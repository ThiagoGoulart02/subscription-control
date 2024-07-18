package scaa.project.com.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scaa.project.com.application.dto.user.request.UserDTO;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"user\"")
    private String user;

    private String password;

    public User(UserDTO dto) {
        this.user = dto.user();
        this.password = dto.password();
    }
}
