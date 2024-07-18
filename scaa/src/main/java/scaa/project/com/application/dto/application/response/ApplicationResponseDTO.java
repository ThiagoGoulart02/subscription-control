package scaa.project.com.application.dto.application.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApplicationResponseDTO {

    private Long id;

    private String name;

    private float monthlyCost;
}
