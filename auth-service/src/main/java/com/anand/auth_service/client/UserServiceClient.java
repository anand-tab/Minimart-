package com.anand.auth_service.client;



import com.anand.auth_service.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-service", url = "http://localhost:8081")
public interface UserServiceClient {

    @PostMapping("/api/users")
    User createUser(@RequestBody User user);
}
