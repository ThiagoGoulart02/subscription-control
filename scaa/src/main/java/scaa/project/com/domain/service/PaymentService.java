package scaa.project.com.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import scaa.project.com.application.dto.payment.request.PaymentDTO;
import scaa.project.com.application.dto.payment.response.PaymentResponseDTO;
import scaa.project.com.domain.repository.PaymentRepositoryImpl;
import scaa.project.com.infrastructure.persistence.SignatureRepository;

@Service
public class PaymentService implements PaymentRepositoryImpl {

    @Autowired
    private SignatureRepository signatureRepository;

    public ResponseEntity<PaymentResponseDTO> createPayment(PaymentDTO dto) {
        var signature = signatureRepository.findById(dto.signatureId());

        if (!signature.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        if (signature.get().getApplication().getMonthlyCost() < dto.amountPaid())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        return null;
    }
}
