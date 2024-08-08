package scaa.project.com.application.useCases.signature;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;
import scaa.project.com.domain.enums.SignatureType;
import scaa.project.com.domain.service.SignatureService;

@Component
public class GetSignatureCase {

    @Autowired
    private SignatureService service;

    public ResponseEntity<List<SignatureResponseDTO>> getSignaturesByType(SignatureType type) {
        return service.getSignaturesByType(type);
    }
}