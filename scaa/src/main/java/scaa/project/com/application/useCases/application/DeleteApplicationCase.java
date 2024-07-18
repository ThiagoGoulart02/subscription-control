package scaa.project.com.application.useCases.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.domain.service.ApplicationService;

@Component
public class DeleteApplicationCase {

    @Autowired
    private ApplicationService service;

    public ResponseEntity<?> deleteApplication(Long id) {
        return service.deleteApplication(id);
    }
}