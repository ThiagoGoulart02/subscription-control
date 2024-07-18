package scaa.project.com.application.useCases.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.application.response.ApplicationResponseDTO;
import scaa.project.com.domain.service.ApplicationService;

import java.util.List;

@Component
public class GetApplicationsCase {

    @Autowired
    private ApplicationService service;

    public ResponseEntity<List<ApplicationResponseDTO>> getApplications() {
        return service.getApplications();
    }
}