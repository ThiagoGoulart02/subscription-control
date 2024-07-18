package scaa.project.com.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scaa.project.com.application.dto.signature.request.SignatureDTO;
import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;
import scaa.project.com.application.useCases.application.DeleteApplicationCase;
import scaa.project.com.application.useCases.signature.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("*")
public class SignatureController {

    @Autowired
    private CreateSignatureCase createSignatureCase;

    @Autowired
    private GetSignatureCase getSignatureCase;

    @Autowired
    private GetSignatureByApplicationCase getSignatureByApplicationCase;

    @Autowired
    private GetSignatureByCustomerCase getSignatureByCustomerCase;

    @Autowired
    private GetSignaturesCase getSignaturesCase;

    @Autowired
    private UpdateSignatureCase updateSignatureCase;

    @Autowired
    private DeleteApplicationCase deleteApplicationCase;

    @PostMapping("/create-signature")
    public ResponseEntity<SignatureResponseDTO> createSignature(@RequestBody @Valid SignatureDTO dto) {
        return createSignatureCase.createSignature(dto);
    }

    @GetMapping("/get-signature/{id}")
    public ResponseEntity<SignatureResponseDTO> getSignature(@PathVariable Long id) {
        return getSignatureCase.getSignature(id);
    }

    @GetMapping("/get-signature/by-application/{id}")
    public ResponseEntity<List<SignatureResponseDTO>> getSignatureByApplication(@PathVariable Long id) {
        return getSignatureByApplicationCase.getSignatureApplicationCase(id);
    }

    @GetMapping("/get-signature/by-customer/{id}")
    public ResponseEntity<List<SignatureResponseDTO>> getSignatureCustomer(@PathVariable Long id) {
        return getSignatureByCustomerCase.getSignatureByCustomer(id);
    }

    @GetMapping("/get-signatures")
    public ResponseEntity<List<SignatureResponseDTO>> getSignatures() {
        return getSignaturesCase.getSignatures();
    }

    @PutMapping("/update-signatures/{id}")
    public ResponseEntity<SignatureResponseDTO> updateSignature(@PathVariable Long id, @RequestBody @Valid SignatureDTO dto) {
        return updateSignatureCase.updateSignature(id, dto);
    }

    @DeleteMapping("/delete-signatures/{id}")
    public ResponseEntity<?> deleteSignature(@PathVariable Long id) {
        return deleteApplicationCase.deleteApplication(id);
    }
}