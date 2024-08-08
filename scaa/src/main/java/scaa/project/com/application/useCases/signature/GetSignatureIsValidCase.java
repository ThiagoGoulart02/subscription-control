package scaa.project.com.application.useCases.signature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import scaa.project.com.domain.service.SignatureService;

@Component
public class GetSignatureIsValidCase {

    @Autowired
    private SignatureService service;

    public ResponseEntity<Boolean> getSignatureIsValid(Long id) {
        return service.getSignatureIsValid(id);
    }
}
