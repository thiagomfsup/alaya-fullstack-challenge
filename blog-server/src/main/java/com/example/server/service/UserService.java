package com.example.server.service;

import com.example.server.controller.dto.UserSignInResultDTO;
import com.example.server.models.User;

public interface UserService {
    UserSignInResultDTO signIn(User user);
}
