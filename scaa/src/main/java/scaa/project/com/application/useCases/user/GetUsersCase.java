package scaa.project.com.application.useCases.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.user.response.UserResponseDTO;
import scaa.project.com.domain.service.UserService;

import java.util.List;

@Component
public class GetUsersCase {

    @Autowired
    private UserService service;

    public ResponseEntity<List<UserResponseDTO>> getUsers(){
        return service.getUsers();
    }
}
