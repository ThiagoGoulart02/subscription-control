package scaa.project.com.application.dto.payment.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PaymentDTO(
        @NotNull LocalDate paymentDate,
        @NotNull Long signatureId,
        @NotNull @Positive Double amountPaid) {
}