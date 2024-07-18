package scaa.project.com.application.useCases.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.user.request.UserDTO;
import scaa.project.com.application.dto.user.response.UserResponseDTO;
import scaa.project.com.domain.service.UserService;

@Component
public class CreateUserCase {

    @Autowired
    private UserService service;

    public ResponseEntity<UserResponseDTO> createUser(UserDTO dto) {
        return service.createUser(dto);
    }
}
