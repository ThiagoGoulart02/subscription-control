package scaa.project.com.application.dto.customer.request;

import jakarta.validation.constraints.NotBlank;

public record CustomerDTO(
        @NotBlank
        String name,
        @NotBlank
        String email
) {
}
