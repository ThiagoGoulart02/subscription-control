package scaa.project.com.application.dto.payment.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PaymentResponseDTO {
    private LocalDate paymentDate;
    private float paymentReversal;
    private String status;
}