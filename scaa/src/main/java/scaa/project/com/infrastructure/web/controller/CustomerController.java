package scaa.project.com.infrastructure.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import scaa.project.com.application.dto.customer.response.CustomerResponseDTO;
import scaa.project.com.application.useCases.customer.GetCustumersCase;

@RestController
@CrossOrigin("*")
@RequestMapping("/servcad")
public class CustomerController {

    @Autowired
    private GetCustumersCase getCostumersCase;

    /*
     * @PostMapping("/create-customer")
     * public ResponseEntity<CustomerResponseDTO> createCustomer(@RequestBody @Valid
     * CustomerDTO dto) {
     * return createCustomerCase.createCustomer(dto);
     * }
     */

    /*
     * @GetMapping("/get-customer/{id}")
     * public ResponseEntity<CustomerResponseDTO> getCustomer(@PathVariable Long id)
     * {
     * return getCostumerCase.getCustomer(id);
     * }
     */

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        return getCostumersCase.getCustomers();
    }

    /*
     * @PutMapping("/update-customer/{id}")
     * public ResponseEntity<CustomerResponseDTO> updateCustomer(@PathVariable Long
     * id,
     * 
     * @RequestBody @Valid CustomerDTO dto) {
     * return updateCustomerCaseCustomerCase.updateCustomer(id, dto);
     * }
     * 
     * @DeleteMapping("/delete-customer/{id}")
     * public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
     * return deleteCustomerCase.deleteCustomer(id);
     * }
     */
}
