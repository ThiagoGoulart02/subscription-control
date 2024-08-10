package ass_cache.project.com.application.useCases.signature;

import ass_cache.project.com.application.dto.signature.response.SignatureResponseDTO;
import ass_cache.project.com.domain.service.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VerifySignature {

    @Autowired
    SignatureService service;

    public ResponseEntity<SignatureResponseDTO> verifySignature(Long id) {
        return service.verifySignature(id);
    }
}