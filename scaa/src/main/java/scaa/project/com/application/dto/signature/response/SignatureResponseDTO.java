package scaa.project.com.application.dto.signature.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import scaa.project.com.domain.entity.Application;
import scaa.project.com.domain.entity.Customer;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class SignatureResponseDTO {

    private Long id;

    private Application application;

    private Customer customer;

    private LocalDate beginningTerm;

    private LocalDate endTerm;
}
