package com.anand.auth_service.controller;

import com.anand.auth_service.dto.AuthResponse;
import com.anand.auth_service.dto.LoginRequest;
import com.anand.auth_service.dto.RegisterReqByAdmin;
import com.anand.auth_service.dto.RegisterRequest;
import com.anand.auth_service.entity.AuthUser;
import com.anand.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    //Register User
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest){
        authService.registerUser(registerRequest);
        return ResponseEntity.ok("User registered successfully");
    }


    //login user
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        log.info("logged in");
        return ResponseEntity.ok(authService.login(request));
    }


    //Register by admin
    @PreAuthorize("hasAuthority('ADMIN')") //it allows only request with role as admin to access the request
    @PostMapping("/admin/register")
    public ResponseEntity<String> registerByAdmin(@RequestBody RegisterReqByAdmin registerReqByAdmin){
        authService.registerByAdmin(registerReqByAdmin);
        return ResponseEntity.ok("Profile was registered");
    }



}