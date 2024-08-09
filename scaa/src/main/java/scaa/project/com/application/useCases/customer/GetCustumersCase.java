package scaa.project.com.application.useCases.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import scaa.project.com.application.dto.customer.response.CustomerResponseDTO;
import scaa.project.com.domain.service.CustomerService;

import java.util.List;

@Component
public class GetCustumersCase {

    @Autowired
    private CustomerService service;

    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        return service.getCustomers();
    }
}
