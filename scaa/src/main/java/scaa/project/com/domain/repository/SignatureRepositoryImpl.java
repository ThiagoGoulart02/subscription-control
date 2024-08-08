package scaa.project.com.domain.repository;

import java.util.List;

import org.springframework.http.ResponseEntity;

import scaa.project.com.application.dto.signature.request.SignatureDTO;
import scaa.project.com.application.dto.signature.response.SignatureResponseDTO;
import scaa.project.com.domain.enums.SignatureType;

public interface SignatureRepositoryImpl {

    ResponseEntity<SignatureResponseDTO> createSignature(SignatureDTO dto);

    ResponseEntity<List<SignatureResponseDTO>> getSignaturesByType(SignatureType type);

    ResponseEntity<List<SignatureResponseDTO>> getSignatureByCustomer(Long id);

    ResponseEntity<List<SignatureResponseDTO>> getSignatureByApplication(Long id);

    ResponseEntity<Boolean> getSignatureIsValid(Long id);

    /*
     * ResponseEntity<SignatureResponseDTO> getSignature(Long id);
     * 
     * ResponseEntity<List<SignatureResponseDTO>> getSignatureByApplication(Long
     * id);
     * 
     * ResponseEntity<List<SignatureResponseDTO>> getSignatureByCustomer(Long id);
     * 
     * ResponseEntity<List<SignatureResponseDTO>> getSignatures();
     * 
     * ResponseEntity<SignatureResponseDTO> updateSignature(Long id, SignatureDTO
     * dto);
     * 
     * ResponseEntity<?> deleteSignature(Long id);
     */
}
