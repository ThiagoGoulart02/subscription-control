package scaa.project.com.domain.repository;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import scaa.project.com.application.dto.user.request.UserDTO;
import scaa.project.com.application.dto.user.response.UserResponseDTO;

import java.util.List;

public interface UserRepositoryImpl {

    ResponseEntity<UserResponseDTO> createUser(UserDTO dto);

    ResponseEntity<UserResponseDTO> getUser(Long id);

    ResponseEntity<List<UserResponseDTO>> getUsers();

    ResponseEntity<UserResponseDTO> updateUser(Long id, UserDTO dto);

    ResponseEntity<?> deleteUser(Long id);
}
