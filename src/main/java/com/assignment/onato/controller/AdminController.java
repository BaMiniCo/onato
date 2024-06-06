package com.assignment.onato.controller;

import com.assignment.onato.entity.Admin;
import com.assignment.onato.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<?> createAdmin(@RequestBody Admin admin, Authentication authentication) {
        // Only existing ADMIN can create a new ADMIN
        // Check if the authenticated user is ADMIN
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return ResponseEntity.status(403).body("Access denied");
        }
        return ResponseEntity.ok(adminService.createAdmin(admin));
    }
}
