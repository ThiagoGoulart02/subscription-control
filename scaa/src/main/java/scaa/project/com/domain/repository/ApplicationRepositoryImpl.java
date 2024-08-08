package scaa.project.com.domain.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import scaa.project.com.application.dto.application.request.ApplicationDTO;
import scaa.project.com.application.dto.application.response.ApplicationResponseDTO;

public interface ApplicationRepositoryImpl {

    /*
     * ResponseEntity<ApplicationResponseDTO> createApplication(ApplicationDTO dto);
     * 
     * ResponseEntity<ApplicationResponseDTO> getApplication(Long id);
     */

    ResponseEntity<List<ApplicationResponseDTO>> getApplications();

    ResponseEntity<ApplicationResponseDTO> updateApplication(Long id, ApplicationDTO dto);

    /* ResponseEntity<?> deleteApplication(Long id); */
}