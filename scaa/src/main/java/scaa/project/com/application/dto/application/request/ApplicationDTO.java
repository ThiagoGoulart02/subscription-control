package scaa.project.com.application.dto.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ApplicationDTO(
        @NotBlank
        String name,
        @NotNull
        float monthlyCost
) {
}
