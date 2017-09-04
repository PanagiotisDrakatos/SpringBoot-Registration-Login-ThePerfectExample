package com.github.tutorial.Service;


import com.github.tutorial.Persistence.model.User;
import com.github.tutorial.Web.dto.UserDto;

public interface UserService {
    User findUserByEmail(String email);

    void createUserAccount(UserDto user);
}
