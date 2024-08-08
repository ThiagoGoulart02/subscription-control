package scaa.project.com.application.useCases.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import scaa.project.com.application.dto.payment.request.PaymentDTO;
import scaa.project.com.application.dto.payment.response.PaymentResponseDTO;
import scaa.project.com.domain.service.PaymentService;

@Component
public class CreatePaymentCase {

    @Autowired
    private PaymentService service;

    public ResponseEntity<PaymentResponseDTO> createPayment(PaymentDTO dto) {
        return service.createPayment(dto);
    }
}