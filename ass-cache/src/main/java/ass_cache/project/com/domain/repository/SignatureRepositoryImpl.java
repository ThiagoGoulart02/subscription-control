package ass_cache.project.com.domain.repository;

import ass_cache.project.com.application.dto.signature.response.SignatureResponseDTO;
import org.springframework.http.ResponseEntity;

public interface SignatureRepositoryImpl {

    ResponseEntity<SignatureResponseDTO> verifySignature(Long id);
}