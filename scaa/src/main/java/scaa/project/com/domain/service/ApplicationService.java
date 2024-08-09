package scaa.project.com.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import scaa.project.com.application.dto.application.request.ApplicationDTO;
import scaa.project.com.application.dto.application.response.ApplicationResponseDTO;
import scaa.project.com.domain.repository.ApplicationRepositoryImpl;
import scaa.project.com.infrastructure.persistence.ApplicationRepository;

@Service
public class ApplicationService implements ApplicationRepositoryImpl {

    @Autowired
    private ApplicationRepository repository;

    public ResponseEntity<List<ApplicationResponseDTO>> getApplications() {
        List<ApplicationResponseDTO> apps = repository.findAll()
                .stream()
                .map(app -> ApplicationResponseDTO.builder()
                        .id(app.getId())
                        .name(app.getName())
                        .monthlyCost(app.getMonthlyCost())
                        .build()
                )
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(apps);
    }

    public ResponseEntity<ApplicationResponseDTO> updateApplication(Long id, ApplicationDTO dto) {
        var app = repository.findById(id);

        if (app.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        app.get().setMonthlyCost(dto.monthlyCost());

        repository.save(app.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApplicationResponseDTO.builder()
                        .id(app.get().getId())
                        .name(app.get().getName())
                        .monthlyCost(app.get().getMonthlyCost())
                        .build()
                );
    }
}