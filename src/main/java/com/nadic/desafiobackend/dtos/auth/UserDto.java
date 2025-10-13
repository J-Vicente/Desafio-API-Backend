package com.nadic.desafiobackend.dtos.auth;

import com.nadic.desafiobackend.entities.User;
import org.springframework.beans.BeanUtils;

public class UserDto {
    private String username;
    private String password;

    public UserDto() {
    }

    public UserDto(User user) {
        BeanUtils.copyProperties(user,this);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
