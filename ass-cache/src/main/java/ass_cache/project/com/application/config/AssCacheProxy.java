package ass_cache.project.com.application.config;

import ass_cache.project.com.application.dto.signature.response.SignatureResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "scaa", url = "http://localhost:8000")
public interface AssCacheProxy {
    @GetMapping("/servcad/verify-signature/{id}")
    SignatureResponseDTO verifySignature(@PathVariable Long id);
}