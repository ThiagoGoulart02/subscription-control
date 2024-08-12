package ass_cache.project.com.infrastructure.web.controller;

import ass_cache.project.com.application.dto.signature.response.SignatureResponseDTO;
import ass_cache.project.com.application.useCases.signature.VerifySignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("")
public class SignatureController {

    @Autowired
    private VerifySignature verifySignature;

    @GetMapping("/signature-is-valid/{id}")
    public ResponseEntity<SignatureResponseDTO> verifySignature(@PathVariable Long id) {
        return verifySignature.verifySignature(id);
    }
}