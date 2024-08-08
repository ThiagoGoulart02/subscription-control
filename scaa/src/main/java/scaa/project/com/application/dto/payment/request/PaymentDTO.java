package scaa.project.com.application.dto.payment.request;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;

public record PaymentDTO(
                @NotNull Date paymentDate,
                @NotNull Long signatureId,
                @NotNull float amountPaid) {
}