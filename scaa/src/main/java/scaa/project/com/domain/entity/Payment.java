package scaa.project.com.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Signature signature;

    @Column(name = "amount_paid")
    private float amountPaid;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    private String promotion;

    public Payment(Signature signature, float amountPaid, LocalDate paymentDate, String promotion) {
        this.signature = signature;
        this.amountPaid = amountPaid;
        this.paymentDate = paymentDate;
        this.promotion = promotion;
    }
}
