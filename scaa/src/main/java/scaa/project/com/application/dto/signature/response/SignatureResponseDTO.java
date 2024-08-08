package scaa.project.com.application.dto.signature.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class SignatureResponseDTO {

    private Long id;

    private Long applicationId;

    private Long customerId;

    private LocalDate beginningTerm;

    private LocalDate endTerm;

    private String status;
}
