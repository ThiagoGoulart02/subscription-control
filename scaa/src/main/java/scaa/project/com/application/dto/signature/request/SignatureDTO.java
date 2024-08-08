package scaa.project.com.application.dto.signature.request;

import jakarta.validation.constraints.NotNull;

public record SignatureDTO(
        @NotNull Long applicationId,
        @NotNull Long customerId) {
}
