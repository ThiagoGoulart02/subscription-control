package scaa.project.com.application.useCases.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.application.request.ApplicationDTO;
import scaa.project.com.application.dto.application.response.ApplicationResponseDTO;
import scaa.project.com.domain.service.ApplicationService;

@Component
public class CreateApplicationCase {

    @Autowired
    private ApplicationService service;

    public ResponseEntity<ApplicationResponseDTO> createApplication(ApplicationDTO dto){
        return service.createApplication(dto);
    }
}
