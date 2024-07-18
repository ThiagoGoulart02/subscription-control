package scaa.project.com.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scaa.project.com.application.dto.user.request.UserDTO;
import scaa.project.com.application.dto.user.response.UserResponseDTO;
import scaa.project.com.application.useCases.user.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("*")
public class UserController {

    @Autowired
    private CreateUserCase createUserCase;

    @Autowired
    private GetUserCase getUserCase;

    @Autowired
    private GetUsersCase getUsersCase;

    @Autowired
    private UpdateUserCase updateUserCase;

    @Autowired
    private DeleteUserCase deleteUserCase;

    @PostMapping("/create-user")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserDTO dto) {
        return createUserCase.createUser(dto);
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        return getUserCase.getUser(id);
    }

    @GetMapping("/get-users")
    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        return getUsersCase.getUsers();
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserDTO dto){
        return updateUserCase.updateUser(id, dto);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return deleteUserCase.deleteUser(id);
    }
}
