package com.nadic.desafiobackend.dtos.auth;

import com.nadic.desafiobackend.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class UserDto {
    private String username;
    private String password;

    public UserDto(User user) {
        BeanUtils.copyProperties(user,this);
    }


}
