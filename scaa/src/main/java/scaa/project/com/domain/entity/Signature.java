package scaa.project.com.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scaa.project.com.application.dto.signature.request.SignatureDTO;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "signatures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Signature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Application application;

    @ManyToOne
    private Customer customer;

    @Column(name = "beginning_term")
    private LocalDate beginningTerm;

    @Column(name = "end_term")
    private LocalDate endTerm;

    public Signature(Application application, Customer customer, LocalDate beginningTerm, LocalDate endTerm){
        this.application = application;
        this.customer = customer;
        this.beginningTerm = beginningTerm;
        this.endTerm = endTerm;
    }
}