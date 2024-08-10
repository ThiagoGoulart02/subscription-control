package scaa.project.com.application.useCases.signature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;
import scaa.project.com.domain.service.SignatureService;

@Component
public class VerifySignature {

    @Autowired
    private SignatureService service;

    public SignatureResponseDTO verifySignature(Long id) {
        return service.verifySignature(id);
    }
}