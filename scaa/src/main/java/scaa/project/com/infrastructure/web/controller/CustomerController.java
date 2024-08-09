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

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDTO>> getCustomers() {
        return getCostumersCase.getCustomers();
    }

}
