package scaa.project.com.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import scaa.project.com.domain.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

}
