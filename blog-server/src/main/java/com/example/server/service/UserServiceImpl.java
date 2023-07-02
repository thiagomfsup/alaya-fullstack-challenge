package com.example.server.service;

import com.example.server.controller.dto.UserSignInResultDTO;
import com.example.server.models.User;
import com.example.server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserSignInResultDTO signIn(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return UserSignInResultDTO.failure("Username is already registered");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return UserSignInResultDTO.failure("Email is already registered");
        }

        user.setCuid(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);

        userRepository.save(user);

        return UserSignInResultDTO.successSignup("User has been registered");
    }
}
