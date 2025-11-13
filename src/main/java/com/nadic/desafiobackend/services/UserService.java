package com.nadic.desafiobackend.services;

import com.nadic.desafiobackend.auth.JwtUtil;
import com.nadic.desafiobackend.dtos.auth.UserDto;
import com.nadic.desafiobackend.dtos.mappers.UserMapper;
import com.nadic.desafiobackend.entities.User;
import com.nadic.desafiobackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil JwtUtil;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void register(UserDto dto) {

        userRepository.findByUsername(dto.getUsername()).ifPresent(u -> {
            throw new RuntimeException("Username já cadastrado");
        });

        userRepository.save(userMapper.toEntity(dto));
    }

    public String login(UserDto dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        return JwtUtil.generateToken(user);
    }
}
