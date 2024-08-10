package ass_cache.project.com.application.dto.signature.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Getter
public class SignatureResponseDTO {

    private Long id;

    private Long applicationId;

    private Long customerId;

    private LocalDate beginningTerm;

    private LocalDate endTerm;

    private String status;
}