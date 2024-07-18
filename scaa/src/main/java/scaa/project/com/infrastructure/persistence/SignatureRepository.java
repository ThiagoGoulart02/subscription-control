package scaa.project.com.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import scaa.project.com.domain.entity.Signature;

import java.util.List;

public interface SignatureRepository extends JpaRepository<Signature, Long> {

    List<Signature> findByApplicationId(Long applicationId);

    List<Signature> findByCustomerId(Long customerId);
}
