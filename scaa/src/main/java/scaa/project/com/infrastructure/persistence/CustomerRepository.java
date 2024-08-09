package scaa.project.com.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import scaa.project.com.domain.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}