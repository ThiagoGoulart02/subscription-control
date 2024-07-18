package scaa.project.com.application.useCases.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.user.response.UserResponseDTO;
import scaa.project.com.domain.service.UserService;

@Component
public class GetUserCase {

    @Autowired
    private UserService service;

    public ResponseEntity<UserResponseDTO> getUser(Long id){
        return service.getUser(id);
    }
}
