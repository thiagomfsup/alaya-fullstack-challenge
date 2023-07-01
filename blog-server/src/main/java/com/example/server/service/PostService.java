package com.example.server.service;

import com.example.server.models.Post;
import org.springframework.http.ProblemDetail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {
    List<Post> findAll();

    Optional<Post> findById(UUID uuid);

    Post publish(Post post);

    void deleteById(UUID uuid);
}
