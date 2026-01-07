package com.anand.auth_service.service;


import com.anand.auth_service.client.UserServiceClient;
import com.anand.auth_service.dto.RegisterRequest;
import com.anand.auth_service.dto.User;
import com.anand.auth_service.entity.AuthUser;
import com.anand.auth_service.repository.AuthUserRepository;
import com.anand.auth_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    public void registerUser(RegisterRequest registerRequest) {
        if(authUserRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        AuthUser authUser = AuthUser.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role("USER")
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
}
