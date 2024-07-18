package scaa.project.com.domain.repository;

import org.springframework.http.ResponseEntity;
import scaa.project.com.application.dto.customer.request.CustomerDTO;
import scaa.project.com.application.dto.customer.response.CustomerResponseDTO;

import java.util.List;

public interface CustomerRepositoryImpl {

    ResponseEntity<CustomerResponseDTO> createCustomer(CustomerDTO dto);
    ResponseEntity<CustomerResponseDTO> getCustomer(Long id);
    ResponseEntity<List<CustomerResponseDTO>> getCustomers();
    ResponseEntity<CustomerResponseDTO> updateCustomer(Long id, CustomerDTO dto);
    ResponseEntity<?> deleteCustomer(Long id);
}
