package com.example.server.controller.dto;

public record LoginResponseDTO(UserDTO user, String token) {
    public static LoginResponseDTO of(UserDTO user, String token) {
        return new LoginResponseDTO(user, token);
    }
}
