package com.anand.userservice.repository;

import com.anand.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReposiroty extends JpaRepository<User, String> {
}
