package scaa.project.com.application.useCases.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.customer.request.CustomerDTO;
import scaa.project.com.application.dto.customer.response.CustomerResponseDTO;
import scaa.project.com.domain.service.CustomerService;

@Component
public class CreateCustomerCase {

    @Autowired
    private CustomerService service;

    public ResponseEntity<CustomerResponseDTO> createCustomer(CustomerDTO dto) {
        return service.createCustomer(dto);
    }
}