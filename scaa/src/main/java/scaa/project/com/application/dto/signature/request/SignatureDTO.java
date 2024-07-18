package scaa.project.com.application.dto.signature.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SignatureDTO(
        @NotNull
        Long applicationId,
        @NotNull
        Long customerId
) {
}
