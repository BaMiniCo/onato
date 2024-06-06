package com.assignment.onato.service;

import com.assignment.onato.entity.Admin;
import com.assignment.onato.entity.Customer;
import com.assignment.onato.repo.AdminRepository;
import com.assignment.onato.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username).orElse(null);
        if (admin != null) {
            return new User(admin.getUsername(), admin.getPassword(), getAuthority("ROLE_ADMIN"));
        }

        Customer customer = customerRepository.findByUsername(username).orElse(null);
        if (customer != null) {
            return new User(customer.getUsername(), customer.getPassword(), getAuthority("ROLE_CUSTOMER"));
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    private List<SimpleGrantedAuthority> getAuthority(String role) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
