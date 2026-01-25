package com.anand.userservice.service;

import com.anand.userservice.model.User;
import com.anand.userservice.repository.UserReposiroty;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserReposiroty userReposiroty;

    public  User createUser(User user) {

        return userReposiroty.save(user);
    }

    public  User getUserById(String id) {

        return userReposiroty.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> getAllUser() {
        return userReposiroty.findAll();
    }

    public Boolean validateUser(String id) {

        return userReposiroty.existsById(id);
    }
}
