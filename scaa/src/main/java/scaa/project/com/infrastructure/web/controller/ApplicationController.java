package scaa.project.com.infrastructure.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import scaa.project.com.application.dto.application.request.ApplicationDTO;
import scaa.project.com.application.dto.application.response.ApplicationResponseDTO;
import scaa.project.com.application.useCases.application.GetApplicationsCase;
import scaa.project.com.application.useCases.application.UpdateApplicationCase;

@RestController
@CrossOrigin("*")
@RequestMapping("/servcad")
public class ApplicationController {

    @Autowired
    private GetApplicationsCase getApplicationsCase;

    @Autowired
    private UpdateApplicationCase updateApplicationCase;

    @GetMapping("/applications")
    public ResponseEntity<List<ApplicationResponseDTO>> getApplications() {
        return getApplicationsCase.getApplications();
    }

    @PutMapping("/applications/updatecost/{id}")
    public ResponseEntity<ApplicationResponseDTO> updateCost(@PathVariable Long id,
                                                             @RequestBody @Valid ApplicationDTO dto) {
        return updateApplicationCase.updateApplication(id, dto);
    }
}
