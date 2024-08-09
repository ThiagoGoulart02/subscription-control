package scaa.project.com.domain.repository;

import org.springframework.http.ResponseEntity;
import scaa.project.com.application.dto.customer.response.CustomerResponseDTO;

import java.util.List;

public interface CustomerRepositoryImpl {

    ResponseEntity<List<CustomerResponseDTO>> getCustomers();
}
