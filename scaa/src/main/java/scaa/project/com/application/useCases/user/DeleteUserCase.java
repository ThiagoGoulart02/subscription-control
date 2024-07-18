package scaa.project.com.application.useCases.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.domain.service.UserService;

@Component
public class DeleteUserCase {

    @Autowired
    private UserService service;

    public ResponseEntity<?> deleteUser(Long id){
        return service.deleteUser(id);
    }
}
