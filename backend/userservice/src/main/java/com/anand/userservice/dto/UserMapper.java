package com.anand.userservice.dto;

import com.anand.userservice.model.User;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapper {

    //UserRequest to User
    User toEntity(UserRequest userRequest);

    //User to UserResponse
    UserResponse toDto(User user);

    List<UserResponse> toDto(List<User> users);
}
