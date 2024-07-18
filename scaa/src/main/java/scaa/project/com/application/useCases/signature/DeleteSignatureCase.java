package scaa.project.com.application.useCases.signature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.domain.service.SignatureService;

@Component
public class DeleteSignatureCase {

    @Autowired
    private SignatureService service;

    public ResponseEntity<?> deleteSignature(Long id) {
        return service.deleteSignature(id);
    }
}