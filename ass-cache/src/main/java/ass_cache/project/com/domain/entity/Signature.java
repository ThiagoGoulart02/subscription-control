package ass_cache.project.com.domain.entity;

import ass_cache.project.com.application.dto.signature.response.SignatureResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Signature {

    private Long id;

    private Long applicationId;

    private long customerId;

    private LocalDate beginningTerm;

    private LocalDate endTerm;


    public Signature(SignatureResponseDTO dto) {
        this.id = dto.getId();
        this.applicationId = dto.getApplicationId();
        this.customerId = dto.getCustomerId();
        this.beginningTerm = dto.getBeginningTerm();
        this.endTerm = dto.getEndTerm();
    }
}