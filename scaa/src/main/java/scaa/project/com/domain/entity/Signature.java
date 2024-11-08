package scaa.project.com.domain.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "signatures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Signature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Application application;

    @ManyToOne
    private Customer customer;

    @Column(name = "beginning_term")
    private LocalDate beginningTerm;

    @Column(name = "end_term")
    private LocalDate endTerm;

    public Signature(Application application, Customer customer, LocalDate beginningTerm, LocalDate endTerm) {
        this.application = application;
        this.customer = customer;
        this.beginningTerm = beginningTerm;
        this.endTerm = endTerm;
    }
}