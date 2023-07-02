package com.example.server.controller.dto;

public record UserSignInResultDTO(boolean success, String message) {
    public static UserSignInResultDTO successSignup(String message) {
        return new UserSignInResultDTO(true, message);
    }

    public static UserSignInResultDTO failure(String message) {
        return new UserSignInResultDTO(false, message);
    }
}
