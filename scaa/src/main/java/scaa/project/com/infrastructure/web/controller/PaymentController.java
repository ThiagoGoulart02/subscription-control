package scaa.project.com.infrastructure.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import scaa.project.com.application.dto.payment.request.PaymentDTO;
import scaa.project.com.application.dto.payment.response.PaymentResponseDTO;
import scaa.project.com.application.useCases.payment.CreatePaymentCase;

@RestController
@CrossOrigin("*")
@RequestMapping("/servcad")
public class PaymentController {

    @Autowired
    private CreatePaymentCase createPaymentCase;

    @PostMapping("/registerpayment")
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody @Valid PaymentDTO dto) {
        return createPaymentCase.createPayment(dto);
    }
}