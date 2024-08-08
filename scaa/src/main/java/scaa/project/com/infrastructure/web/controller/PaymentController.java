package scaa.project.com.infrastructure.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import scaa.project.com.application.dto.payment.request.PaymentDTO;
import scaa.project.com.application.dto.payment.response.PaymentResponseDTO;
import scaa.project.com.application.useCases.payment.CreatePaymentCase;

@RestController
@RequestMapping
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private CreatePaymentCase createPaymentCase;

    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentDTO dto) {
        return createPaymentCase.createPayment(dto);
    }
}