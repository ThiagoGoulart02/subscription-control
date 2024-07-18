package scaa.project.com.application.useCases.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.customer.response.CustomerResponseDTO;
import scaa.project.com.domain.service.CustomerService;

@Component
public class DeleteCustomerCase {

    @Autowired
    private CustomerService service;

    public ResponseEntity<?> deleteCustomer(Long id) {
        return service.deleteCustomer(id);
    }
}
