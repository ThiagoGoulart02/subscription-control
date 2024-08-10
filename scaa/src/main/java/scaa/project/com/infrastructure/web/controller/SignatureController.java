package scaa.project.com.infrastructure.web.controller;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import scaa.project.com.application.dto.signature.request.SignatureDTO;
import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;
import scaa.project.com.application.useCases.signature.*;
import scaa.project.com.domain.enums.SignatureType;

@RestController
@CrossOrigin("*")
@RequestMapping("/servcad")
public class SignatureController {

    @Autowired
    private CreateSignatureCase createSignatureCase;

    @Autowired
    private GetSignatureCase getSignatureCase;

    @Autowired
    private GetSignatureByCustomerCase getSignatureByCustomerCase;

    @Autowired
    private GetSignatureByApplicationCase getSignatureByApplicationCase;

    @Autowired
    private GetSignatureIsValidCase getSignatureIsValidCase;

    @Autowired
    private VerifySignature verifySignature;

    @PostMapping("/signatures")
    public ResponseEntity<SignatureResponseDTO> createSignature(@RequestBody @Valid SignatureDTO dto) {
        return createSignatureCase.createSignature(dto);
    }

    @GetMapping("/signatures/{type}")
    public ResponseEntity<List<SignatureResponseDTO>> getSignatureByType(@PathVariable SignatureType type) {
        return getSignatureCase.getSignaturesByType(type);
    }

    @GetMapping("/signature-customer/{id}")
    public ResponseEntity<List<SignatureResponseDTO>> getSignatureByCustomer(@PathVariable Long id) {
        return getSignatureByCustomerCase.getSignatureByCustomer(id);
    }

    @GetMapping("/signature-application/{id}")
    public ResponseEntity<List<SignatureResponseDTO>> getSignatureByApplication(@PathVariable Long id) {
        return getSignatureByApplicationCase.getSignatureApplicationCase(id);
    }

    @GetMapping("/signature-is-valid/{id}")
    public ResponseEntity<Boolean> getSignatureIsValid(@PathVariable Long id) {
        return getSignatureIsValidCase.getSignatureIsValid(id);
    }

    @GetMapping("/verify-signature/{id}")
    public SignatureResponseDTO verifySignature(@PathVariable Long id) {
        return verifySignature.verifySignature(id);
    }

}