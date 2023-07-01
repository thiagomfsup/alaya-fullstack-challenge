package com.example.server.controller.dto;

import com.example.server.models.Post;

public record AddPostResponseDTO(Post post) { // TODO should not be Post here
}
