package scaa.project.com.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import scaa.project.com.domain.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}