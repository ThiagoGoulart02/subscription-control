package scaa.project.com.application.dto.payment.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public record PaymentDTO(
        @NotNull LocalDate paymentDate,
        @NotNull Long signatureId,
        @NotNull float amountPaid) {
}