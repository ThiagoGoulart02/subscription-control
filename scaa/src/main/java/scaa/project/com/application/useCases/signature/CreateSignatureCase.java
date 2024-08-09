package scaa.project.com.application.useCases.signature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.signature.request.SignatureDTO;
import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;
import scaa.project.com.domain.service.SignatureService;

@Component
public class CreateSignatureCase {

    @Autowired
    private SignatureService service;

    public ResponseEntity<SignatureResponseDTO> createSignature(SignatureDTO dto) {
        return service.createSignature(dto);
    }
}