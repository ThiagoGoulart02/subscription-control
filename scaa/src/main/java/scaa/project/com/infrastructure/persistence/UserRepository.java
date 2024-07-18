package scaa.project.com.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import scaa.project.com.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUser(String user);

}