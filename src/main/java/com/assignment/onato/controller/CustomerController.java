package com.assignment.onato.controller;

import com.assignment.onato.entity.Customer;
import com.assignment.onato.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer, Authentication authentication) {
        // Only ADMIN can create a new CUSTOMER
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).body("Access denied");
        }
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCustomerDetails(Authentication authentication) {
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"))) {
            return ResponseEntity.status(403).body("Access denied");
        }
        String username = authentication.getName();
        return customerService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateCustomerDetails(@RequestBody Customer customer, Authentication authentication) {
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"))) {
            return ResponseEntity.status(403).body("Access denied");
        }
        String username = authentication.getName();
        return customerService.findByUsername(username)
                .map(existingCustomer -> {
                    existingCustomer.setAddress(customer.getAddress());
                    existingCustomer.setPassword(customer.getPassword());
                    return ResponseEntity.ok(customerService.updateCustomer(existingCustomer));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer id, Authentication authentication) {
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).body("Access denied");
        }
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getAllCustomers(Authentication authentication) {
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).body("Access denied");
        }
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
}
