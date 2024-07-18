package scaa.project.com.infrastructure.web.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scaa.project.com.application.dto.customer.request.CustomerDTO;
import scaa.project.com.application.dto.customer.response.CustomerResponseDTO;
import scaa.project.com.application.useCases.customer.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private CreateCustomerCase createCustomerCase;

    @Autowired
    private GetCustumersCase getCostumersCase;

    @Autowired
    private GetCustumerCase getCostumerCase;

    @Autowired
    private UpdateCustomerCase updateCustomerCaseCustomerCase;

    @Autowired
    private DeleteCustomerCase deleteCustomerCase;

    @PostMapping("/create-customer")
    public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid CustomerDTO dto) {
        return createCustomerCase.createCustomer(dto);
    }

    @GetMapping("/get-customer/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable Long id) {
        return getCostumerCase.getCustomer(id);
    }

    @GetMapping("/get-customers")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        return getCostumersCase.getCustomers();
    }

    @PutMapping("/update-customer/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long id, @RequestBody @Valid CustomerDTO dto) {
        return updateCustomerCaseCustomerCase.updateCustomer(id, dto);
    }

    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        return deleteCustomerCase.deleteCustomer(id);
    }
}
