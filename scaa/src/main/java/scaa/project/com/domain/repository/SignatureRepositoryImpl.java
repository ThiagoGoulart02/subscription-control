package scaa.project.com.domain.repository;

import org.springframework.http.ResponseEntity;
import scaa.project.com.application.dto.signature.request.SignatureDTO;
import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;

import java.util.List;

public interface SignatureRepositoryImpl {

    ResponseEntity<SignatureResponseDTO> createSignature(SignatureDTO dto);

    ResponseEntity<SignatureResponseDTO> getSignature(Long id);

    ResponseEntity<List<SignatureResponseDTO>> getSignatureByApplication(Long id);

    ResponseEntity<List<SignatureResponseDTO>> getSignatureByCustomer(Long id);

    ResponseEntity<List<SignatureResponseDTO>> getSignatures();

    ResponseEntity<SignatureResponseDTO> updateSignature(Long id, SignatureDTO dto);

    ResponseEntity<?> deleteSignature(Long id);
}
