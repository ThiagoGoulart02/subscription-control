package scaa.project.com.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scaa.project.com.application.dto.application.request.ApplicationDTO;
import scaa.project.com.application.dto.application.response.ApplicationResponseDTO;
import scaa.project.com.application.useCases.application.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("*")
public class ApplicationController {

    @Autowired
    private CreateApplicationCase createApplicationCase;

    @Autowired
    private GetApplicationCase getApplicationCase;

    @Autowired
    private GetApplicationsCase getApplicationsCase;

    @Autowired
    private UpdateApplicationCase updateApplicationCase;

    @Autowired
    private DeleteApplicationCase deleteApplicationCase;

    @PostMapping("/create-application")
    public ResponseEntity<ApplicationResponseDTO> createApplication(@RequestBody @Valid ApplicationDTO dto) {
        return createApplicationCase.createApplication(dto);
    }

    @GetMapping("/get-application/{id}")
    public ResponseEntity<ApplicationResponseDTO> getApplication(@PathVariable Long id) {
        return getApplicationCase.getApplication(id);
    }

    @GetMapping("/get-applications")
    public ResponseEntity<List<ApplicationResponseDTO>> getApplications() {
        return getApplicationsCase.getApplications();
    }

    @PutMapping("/update-application/{id}")
    public ResponseEntity<ApplicationResponseDTO> updateApplication(@PathVariable Long id, @RequestBody @Valid ApplicationDTO dto) {
        return updateApplicationCase.updateApplication(id, dto);
    }

    @DeleteMapping("/delete-application/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
        return deleteApplicationCase.deleteApplication(id);
    }
}
