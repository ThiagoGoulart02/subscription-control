package scaa.project.com.domain.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import scaa.project.com.application.config.RabbitMQConfig;
import scaa.project.com.application.dto.payment.request.PaymentDTO;
import scaa.project.com.application.dto.payment.response.PaymentResponseDTO;
import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;
import scaa.project.com.domain.entity.Payment;
import scaa.project.com.domain.repository.PaymentRepositoryImpl;
import scaa.project.com.infrastructure.persistence.PaymentRepository;
import scaa.project.com.infrastructure.persistence.SignatureRepository;

import java.time.LocalDate;

@Service
public class PaymentService implements PaymentRepositoryImpl {

    @Autowired
    private PaymentRepository repository;

    @Autowired
    private SignatureRepository signatureRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ResponseEntity<PaymentResponseDTO> createPayment(PaymentDTO dto) {
        var signature = signatureRepository.findById(dto.signatureId());

        if (signature.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        if (signature.get().getApplication().getMonthlyCost() > dto.amountPaid())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(PaymentResponseDTO.builder()
                            .paymentDate(signature.get().getEndTerm())
                            .paymentReversal(dto.amountPaid())
                            .status("INCORRET_VALUE").build()
                    );

        if (signature.get().getEndTerm().isBefore(LocalDate.now())) {
            signature.get().setBeginningTerm(dto.paymentDate());
            signature.get().setEndTerm(dto.paymentDate().plusDays(30));
        } else {
            signature.get()
                    .setEndTerm(signature.get()
                            .getEndTerm()
                            .plusDays(30)
                    );
        }

        repository.save(new Payment(signature.get(), dto.amountPaid(), dto.paymentDate(), ""));

        if (signature.get().getEndTerm().isAfter(LocalDate.now())) {
            sendMessage(SignatureResponseDTO.builder()
                    .id(signature.get().getId())
                    .applicationId(signature.get().getApplication().getId())
                    .customerId(signature.get().getCustomer().getId())
                    .beginningTerm(signature.get().getBeginningTerm())
                    .endTerm(signature.get().getEndTerm())
                    .status("ACTIVE")
                    .build()
            );
        }

        signatureRepository.save(signature.get());

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(PaymentResponseDTO.builder()
                        .paymentDate(signature.get().getEndTerm())
                        .paymentReversal(0)
                        .status("PAYMENT_OK")
                        .build()
                );
    }

    public void sendMessage(SignatureResponseDTO message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.SUBSCRIPTION_QUEUE, message);
    }
}
