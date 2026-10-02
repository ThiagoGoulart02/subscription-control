package ass_cache.project.com.domain.service;

import ass_cache.project.com.application.config.AssCacheProxy;
import ass_cache.project.com.application.config.RabbitMQConfig;
import ass_cache.project.com.application.dto.signature.response.SignatureResponseDTO;
import ass_cache.project.com.domain.entity.Signature;
import ass_cache.project.com.domain.repository.SignatureRepositoryImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SignatureService implements SignatureRepositoryImpl {

    private static final Logger log = LoggerFactory.getLogger(SignatureService.class);

    @Autowired
    private AssCacheProxy proxy;

    private final ConcurrentHashMap<Long, Signature> signatures = new ConcurrentHashMap<>();

    public ResponseEntity<SignatureResponseDTO> verifySignature(Long id) {

        if (signatures.containsKey(id)) {
            log.debug("Cache hit for signature id={}", id);
            var signature = signatures.get(id);

            if (!signature.isActive()) {
                log.debug("Removing expired signature id={} from cache", id);
                signatures.remove(id);
            }

            return ResponseEntity.status(HttpStatus.OK)
                    .body(SignatureResponseDTO.builder()
                            .id(signature.getId())
                            .applicationId(signature.getApplicationId())
                            .customerId(signature.getCustomerId())
                            .beginningTerm(signature.getBeginningTerm())
                            .endTerm(signature.getEndTerm())
                            .status(signature.resolveStatus())
                            .build()
                    );
        }

        var signature = proxy.verifySignature(id);

        if (signature != null) {
            log.debug("Cache miss for signature id={}, fetched from SCAA", id);
            var cached = new Signature(Objects.requireNonNull(signature));
            if (cached.isActive()) {
                log.debug("Caching active signature id={}", id);
                signatures.put(id, cached);
            }
            signature.setStatus(cached.resolveStatus());
            return ResponseEntity.status(HttpStatus.OK).body(signature);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @RabbitListener(queues = RabbitMQConfig.SUBSCRIPTION_QUEUE)
    public void receiveMessage(SignatureResponseDTO dto) {
        log.info("RabbitMQ message received for signature id={}", dto.getId());
        signatures.put(dto.getId(), new Signature(dto));
        log.debug("Updated cache from queue for signature id={}", dto.getId());
    }
}