package scaa.project.com.application.dto.user.request;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank
        String user,
        @NotBlank
        String password
) {
}