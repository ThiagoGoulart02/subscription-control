package ass_cache.project.com.domain.service;

import ass_cache.project.com.application.config.AssCacheProxy;
import ass_cache.project.com.application.config.RabbitMQConfig;
import ass_cache.project.com.application.dto.signature.response.SignatureResponseDTO;
import ass_cache.project.com.domain.entity.Signature;
import ass_cache.project.com.domain.repository.SignatureRepositoryImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

@Service
public class SignatureService implements SignatureRepositoryImpl {

    @Autowired
    private AssCacheProxy proxy;

    private HashMap<Long, Signature> signatures = new HashMap<>();

    public ResponseEntity<SignatureResponseDTO> verifySignature(Long id) {

        if (signatures.containsKey(id)) {
            System.out.println("Get in the DB");
            var signature = signatures.get(id);

            if (signature.getEndTerm().isBefore(LocalDate.now())) {
                System.out.println("removed from DB");
                signatures.remove(id);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(SignatureResponseDTO.builder()
                            .id(signature.getId())
                            .applicationId(signature.getApplicationId())
                            .customerId(signature.getCustomerId())
                            .beginningTerm(signature.getBeginningTerm())
                            .endTerm(signature.getEndTerm())
                            .status(signature.getEndTerm()
                                    .isAfter(LocalDate.now()) ? "ACTIVE" : "CANCELED")
                            .build()
                    );
        }

        var signature = proxy.verifySignature(id);

        if (signature != null) {
            System.out.println("Looked into scaa");
            if (Objects.requireNonNull(signature).getEndTerm().isAfter(LocalDate.now())) {
                System.out.println("Saved into DB");
                signatures.put(id, new Signature(signature));
            }
            signature.setStatus(signature
                    .getEndTerm()
                    .isAfter(LocalDate.now()) ? "ACTIVE" : "CANCELED");
            return ResponseEntity.status(HttpStatus.OK).body(signature);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RabbitListener(queues = RabbitMQConfig.SUBSCRIPTION_QUEUE)
    public void receiveMessage(SignatureResponseDTO dto) {
        System.out.println(dto);
        signatures.put(dto.getId(), new Signature(dto));
        System.out.println("================== MESSAGE CONSUMED ==================");
        System.err.println("Saved in the DB");
    }
}