package scaa.project.com.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import scaa.project.com.application.dto.customer.response.CustomerResponseDTO;
import scaa.project.com.domain.repository.CustomerRepositoryImpl;
import scaa.project.com.infrastructure.persistence.CustomerRepository;

@Service
public class CustomerService implements CustomerRepositoryImpl {

    @Autowired
    private CustomerRepository repository;

    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        List<CustomerResponseDTO> customers = repository.findAll()
                .stream()
                .map(customer -> CustomerResponseDTO.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .email(customer.getEmail())
                        .build()
                )
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
}
