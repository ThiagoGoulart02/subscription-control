package scaa.project.com.domain.repository;

import org.springframework.http.ResponseEntity;

import scaa.project.com.application.dto.payment.request.PaymentDTO;
import scaa.project.com.application.dto.payment.response.PaymentResponseDTO;

public interface PaymentRepositoryImpl {
    ResponseEntity<PaymentResponseDTO> createPayment(PaymentDTO dto);
}