package scaa.project.com.application.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;

    private String user;

    private String password;
}
