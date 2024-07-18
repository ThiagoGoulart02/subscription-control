package scaa.project.com.application.useCases.signature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;
import scaa.project.com.domain.service.SignatureService;

import java.util.List;

@Component
public class GetSignaturesCase {

    @Autowired
    private SignatureService service;

    public ResponseEntity<List<SignatureResponseDTO>> getSignatures(){
        return service.getSignatures();
    }
}