package com.assignment.onato;

import com.assignment.onato.entity.Admin;
import com.assignment.onato.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Autowired
    private AdminService adminService;

    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            if (adminService.findByUsername("admin").isEmpty()) {
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword("admin123");
                admin.setName("Initial Admin");
                adminService.createAdmin(admin);
            }
        };
    }
}
