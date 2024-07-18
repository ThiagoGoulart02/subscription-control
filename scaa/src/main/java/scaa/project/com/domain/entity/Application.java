package scaa.project.com.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scaa.project.com.application.dto.application.request.ApplicationDTO;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "monthly_cost")
    private float monthlyCost;

    public Application(ApplicationDTO dto) {
        this.name = dto.name();
        this.monthlyCost = dto.monthlyCost();
    }
}