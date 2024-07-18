package scaa.project.com.application.dto.customer.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerResponseDTO {

    private Long id;

    private String name;

    private String email;
}