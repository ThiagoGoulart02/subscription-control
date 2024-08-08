package scaa.project.com.application.dto.payment.response;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentResponseDTO {
    private String stats;
    private float paymentReversal;
    private Date paymentDate;
}