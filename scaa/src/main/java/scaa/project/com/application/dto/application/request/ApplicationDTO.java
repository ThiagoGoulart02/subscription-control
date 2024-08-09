package scaa.project.com.application.dto.application.request;

import jakarta.validation.constraints.NotNull;

public record ApplicationDTO(
        @NotNull float monthlyCost) {
}
