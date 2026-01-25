package com.anand.auth_service.service;


import com.anand.auth_service.client.UserServiceClient;
import com.anand.auth_service.dto.*;
import com.anand.auth_service.entity.AuthUser;
import com.anand.auth_service.repository.AuthUserRepository;
import com.anand.auth_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    @Autowired
    private AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    @Autowired
    private  UserServiceClient userServiceClient;

    //register user
    public void registerUser(RegisterRequest registerRequest) {
        if(authUserRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        AuthUser authUser = AuthUser.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role("USER")
                .enabled(true)
                .build();

        AuthUser saveAuthUser = authUserRepository.save(authUser);
        User user1 = User.builder()
                .email(registerRequest.getEmail())
                .name(registerRequest.getName())
                .build();

        log.info("Now posting to user-service");
            User user = userServiceClient.createUser(user1);

            log.info("User is created");
        return;
    }

    //login
    public AuthResponse login(LoginRequest request) {
        AuthUser user = authUserRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(
                user.getAuthId(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(token);
    }


    //Profile registered by ADMIN
    public void registerByAdmin(RegisterReqByAdmin registerReqByAdmin) {
        if(authUserRepository.findByEmail(registerReqByAdmin.getEmail()).isPresent()){
            throw new RuntimeException("Email already exist");
        }
        log.info("Email is verified");
        AuthUser authUser = AuthUser.builder()
                .email(registerReqByAdmin.getEmail())
                .password(passwordEncoder.encode(registerReqByAdmin.getPassword()))
                .role(registerReqByAdmin.getRole())
                .enabled(true)
                .build();

        AuthUser authUser1 = authUserRepository.save(authUser);

        log.info("user is saved in db");

        User user = User.builder()
                .name(registerReqByAdmin.getName())
                .email(registerReqByAdmin.getEmail())
                .build();

        log.info("Profile is being saved in user");
        User user1 = userServiceClient.createUser(user);


        log.info("Profile is registered in user db");
        return;

    }


}
