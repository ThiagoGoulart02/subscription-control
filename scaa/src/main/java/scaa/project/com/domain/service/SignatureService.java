package scaa.project.com.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import scaa.project.com.application.dto.signature.request.SignatureDTO;
import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;
import scaa.project.com.domain.entity.Application;
import scaa.project.com.domain.entity.Customer;
import scaa.project.com.domain.entity.Signature;
import scaa.project.com.domain.enums.SignatureType;
import scaa.project.com.domain.repository.SignatureRepositoryImpl;
import scaa.project.com.infrastructure.persistence.ApplicationRepository;
import scaa.project.com.infrastructure.persistence.CustomerRepository;
import scaa.project.com.infrastructure.persistence.SignatureRepository;

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
        if (customer == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        Application application = applicationRepository.findById(dto.applicationId()).orElse(null);
        if (application == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        boolean signatureExists = repository.findByCustomerId(customer.getId())
                .stream()
                .anyMatch(sig -> sig.getApplication()
                        .getId()
                        .equals(application.getId()
                        )
                );

        if (signatureExists) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        LocalDate beginningTerm = LocalDate.now();
        LocalDate endTerm = LocalDate.now().plusDays(7);

        var signature = repository.save(new Signature(application, customer, beginningTerm, endTerm));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SignatureResponseDTO.builder()
                        .id(signature.getId())
                        .applicationId(signature.getApplication().getId())
                        .customerId(signature.getCustomer().getId())
                        .beginningTerm(signature.getBeginningTerm())
                        .status("ACTIVE")
                        .endTerm(signature.getEndTerm()).build()
                );
    }

    public ResponseEntity<List<SignatureResponseDTO>> getSignaturesByType(SignatureType type) {
        List<SignatureResponseDTO> signatures;
        switch (type) {
            case ALL:
                signatures = repository.findAll()
                        .stream()
                        .map(signature -> SignatureResponseDTO.builder()
                                .id(signature.getId())
                                .applicationId(signature.getApplication().getId())
                                .customerId(signature.getCustomer().getId())
                                .beginningTerm(signature.getBeginningTerm())
                                .endTerm(signature.getEndTerm())
                                .status(signature.getEndTerm()
                                        .isAfter(LocalDate.now()) ? "ACTIVE"
                                        : "CANCELED")
                                .build()
                        )
                        .toList();
                return ResponseEntity.status(HttpStatus.OK).body(signatures);
            case ACTIVES:
                signatures = repository.findAll()
                        .stream()
                        .filter(signature -> signature.getEndTerm()
                                .isAfter(LocalDate.now()))
                        .map(signature -> SignatureResponseDTO.builder()
                                .id(signature.getId())
                                .applicationId(signature.getApplication().getId())
                                .customerId(signature.getCustomer().getId())
                                .beginningTerm(signature.getBeginningTerm())
                                .endTerm(signature.getEndTerm())
                                .status("ACTIVE")
                                .build()
                        )
                        .collect(Collectors.toList());
                break;
            case CANCELED:
                signatures = repository.findAll()
                        .stream()
                        .filter(signature -> signature.getEndTerm()
                                .isBefore(LocalDate.now()))
                        .map(signature -> SignatureResponseDTO.builder()
                                .id(signature.getId())
                                .applicationId(signature.getApplication().getId())
                                .customerId(signature.getCustomer().getId())
                                .beginningTerm(signature.getBeginningTerm())
                                .endTerm(signature.getEndTerm())
                                .status("CANCELED")
                                .build()
                        )
                        .collect(Collectors.toList());
                break;
            default:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(signatures);
    }

    public ResponseEntity<List<SignatureResponseDTO>> getSignatureByCustomer(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(repository.findByCustomerId(id)
                        .stream()
                        .map(signature -> SignatureResponseDTO.builder()
                                .id(signature.getId())
                                .applicationId(signature.getApplication().getId())
                                .customerId(signature.getCustomer().getId())
                                .beginningTerm(signature.getBeginningTerm())
                                .endTerm(signature.getEndTerm())
                                .status(signature.getEndTerm()
                                        .isAfter(LocalDate.now()) ? "ACTIVE"
                                        : "CANCELED")
                                .build()
                        )
                        .toList());
    }

    public ResponseEntity<List<SignatureResponseDTO>> getSignatureByApplication(Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(repository.findByApplicationId(id)
                        .stream()
                        .map(signature -> SignatureResponseDTO.builder()
                                .id(signature.getId())
                                .applicationId(signature.getApplication().getId())
                                .customerId(signature.getCustomer().getId())
                                .beginningTerm(signature.getBeginningTerm())
                                .endTerm(signature.getEndTerm())
                                .status(signature.getEndTerm()
                                        .isAfter(LocalDate.now()) ? "ACTIVE"
                                        : "CANCELED")
                                .build()
                        )
                        .toList());
    }

    public ResponseEntity<Boolean> getSignatureIsValid(Long id) {
        var signature = repository.findById(id);

        return signature.map(value -> ResponseEntity.status(HttpStatus.OK)
                        .body(value.getEndTerm()
                                .isAfter(LocalDate.now())
                        )
                )
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));

    }
}