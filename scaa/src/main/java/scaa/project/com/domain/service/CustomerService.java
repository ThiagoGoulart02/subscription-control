package scaa.project.com.domain.service;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import scaa.project.com.application.dto.customer.request.CustomerDTO;
import scaa.project.com.application.dto.customer.response.CustomerResponseDTO;
import scaa.project.com.domain.entity.Customer;
import scaa.project.com.domain.repository.CustomerRepositoryImpl;
import scaa.project.com.infrastructure.persistence.CustomerRepository;

import java.util.List;

@Service
public class CustomerService implements CustomerRepositoryImpl {

    @Autowired
    private CustomerRepository repository;

    public ResponseEntity<CustomerResponseDTO> createCustomer(CustomerDTO dto) {
        if (repository.findByEmail(dto.email()) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        var customer = repository.save(new Customer(dto));

        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomerResponseDTO.builder()
                        .id(customer.getId())
                        .name(customer.getName())
                        .email(customer.getEmail())
                        .build()
                );
    }

    public ResponseEntity<CustomerResponseDTO> getCustomer(Long id) {
        return repository.findById(id)
                .map(customer -> ResponseEntity.status(HttpStatus.OK)
                        .body(CustomerResponseDTO.builder()
                                .id(customer.getId())
                                .name(customer.getName())
                                .email(customer.getEmail())
                                .build()
                        ))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

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

    public ResponseEntity<CustomerResponseDTO> updateCustomer(Long id, CustomerDTO dto) {
        var customer = repository.findById(id);

        if (repository.findById(id).isEmpty() || !customer.get().getEmail().equals(dto.email()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        customer.get().setName(dto.name());
        repository.save(customer.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomerResponseDTO.builder()
                        .id(customer.get().getId())
                        .name(customer.get().getName())
                        .email(customer.get().getEmail())
                        .build()
                );
    }

    public ResponseEntity<?> deleteCustomer(Long id) {
        if (repository.findById(id).isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        repository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
