package scaa.project.com.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import scaa.project.com.application.dto.signature.request.SignatureDTO;
import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;
import scaa.project.com.domain.entity.Application;
import scaa.project.com.domain.entity.Customer;
import scaa.project.com.domain.entity.Signature;
import scaa.project.com.domain.repository.SignatureRepositoryImpl;
import scaa.project.com.infrastructure.persistence.ApplicationRepository;
import scaa.project.com.infrastructure.persistence.CustomerRepository;
import scaa.project.com.infrastructure.persistence.SignatureRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class SignatureService implements SignatureRepositoryImpl {

    @Autowired
    private SignatureRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    public ResponseEntity<SignatureResponseDTO> createSignature(SignatureDTO dto) {
        Customer customer = customerRepository.findById(dto.customerId()).orElse(null);
        if (customer == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        Application application = applicationRepository.findById(dto.applicationId()).orElse(null);
        if (application == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        LocalDate beginningTerm = LocalDate.now();
        LocalDate endTerm = LocalDate.now().plusDays(7);

        var signature = repository.save(new Signature(application, customer, beginningTerm, endTerm));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SignatureResponseDTO.builder()
                        .id(signature.getId())
                        .application(signature.getApplication())
                        .customer(signature.getCustomer())
                        .beginningTerm(signature.getBeginningTerm())
                        .endTerm(signature.getEndTerm()).build()
                );
    }

    public ResponseEntity<SignatureResponseDTO> getSignature(Long id) {
        return repository.findById(id)
                .map(signature -> ResponseEntity.status(HttpStatus.OK)
                        .body(SignatureResponseDTO.builder()
                                .id(signature.getId())
                                .application(signature.getApplication())
                                .customer(signature.getCustomer())
                                .beginningTerm(signature.getBeginningTerm())
                                .endTerm(signature.getEndTerm())
                                .build()
                        ))
                .orElse(ResponseEntity.status(HttpStatus.OK).body(null));
    }

    public ResponseEntity<List<SignatureResponseDTO>> getSignatureByApplication(Long id) {
        /*return ResponseEntity.status(HttpStatus.OK)
                .body(repository.findAll()
                        .stream()
                        .filter(signature -> Objects.equals(signature.getApplication()
                                .getId(), id)).map(signature -> SignatureResponseDTO.builder()
                                .id(signature.getId())
                                .application(signature.getApplication())
                                .customer(signature.getCustomer())
                                .beginningTerm(signature.getBeginningTerm())
                                .endTerm(signature.getEndTerm())
                                .build()
                        )
                        .toList()
                );*/
        return ResponseEntity.status(HttpStatus.OK).body(
                repository.findByApplicationId(id)
                        .stream()
                        .map(signature -> SignatureResponseDTO.builder()
                                .id(signature.getId())
                                .application(signature.getApplication())
                                .customer(signature.getCustomer())
                                .beginningTerm(signature.getBeginningTerm())
                                .endTerm(signature.getEndTerm())
                                .build()
                        )
                        .toList()
        );
    }

    public ResponseEntity<List<SignatureResponseDTO>> getSignatureByCustomer(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(repository.findByCustomerId(id)
                        .stream()
                        .map(signature -> SignatureResponseDTO.builder()
                                .id(signature.getId())
                                .application(signature.getApplication())
                                .customer(signature.getCustomer())
                                .beginningTerm(signature.getBeginningTerm())
                                .endTerm(signature.getEndTerm())
                                .build()
                        )
                        .toList()
                );
    }

    public ResponseEntity<List<SignatureResponseDTO>> getSignatures() {
        List<SignatureResponseDTO> signatures = repository.findAll()
                .stream()
                .map(signature -> SignatureResponseDTO.builder()
                        .id(signature.getId())
                        .application(signature.getApplication())
                        .customer(signature.getCustomer())
                        .beginningTerm(signature.getBeginningTerm())
                        .endTerm(signature.getEndTerm())
                        .build()
                )
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(signatures);
    }

    public ResponseEntity<SignatureResponseDTO> updateSignature(Long id, SignatureDTO dto) {
        return null;
    }

    public ResponseEntity<?> deleteSignature(Long id) {
        return null;
    }
}
