package com.example.server.controller.dto;

import com.example.server.models.Post;

import java.util.List;

public record ListOfPostsResponseDTO(List<Post> posts) {
    public static ListOfPostsResponseDTO of(List<Post> posts) {
        return new ListOfPostsResponseDTO(posts);
    }
}
