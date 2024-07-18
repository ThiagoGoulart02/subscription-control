package scaa.project.com.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import scaa.project.com.application.dto.user.request.UserDTO;
import scaa.project.com.application.dto.user.response.UserResponseDTO;
import scaa.project.com.domain.entity.User;
import scaa.project.com.domain.repository.UserRepositoryImpl;
import scaa.project.com.infrastructure.persistence.UserRepository;

import java.util.List;

@Service
public class UserService implements UserRepositoryImpl {

    @Autowired
    private UserRepository repository;

    public ResponseEntity<UserResponseDTO> createUser(UserDTO dto) {
        if (repository.findByUser(dto.user()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        var user = repository.save(new User(dto));

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(UserResponseDTO.builder()
                        .id(user.getId())
                        .user(dto.user())
                        .password(dto.password())
                        .build()
                );
    }

    public ResponseEntity<UserResponseDTO> getUser(Long id) {
        return repository.findById(id)
                .map(user -> ResponseEntity.status(HttpStatus.OK)
                        .body(UserResponseDTO.builder()
                                .id(user.getId())
                                .user(user.getUser())
                                .password(user.getPassword())
                                .build()
                        ))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<List<UserResponseDTO>> getUsers() {
        List<UserResponseDTO> users = repository.findAll()
                .stream()
                .map(user -> UserResponseDTO.builder()
                        .id(user.getId())
                        .user(user.getUser())
                        .password(user.getPassword())
                        .build())
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    public ResponseEntity<UserResponseDTO> updateUser(Long id, UserDTO dto) {
        var user = repository.findById(id);

        if (user.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        if (repository.findByUser(dto.user()) != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        user.get().setUser(dto.user());
        user.get().setPassword(dto.password());
        repository.save(user.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body(UserResponseDTO.builder()
                        .id(user.get().getId())
                        .user(user.get().getUser())
                        .password(user.get().getPassword())
                        .build()
                );
    }

    public ResponseEntity<?> deleteUser(Long id) {
        if (repository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
