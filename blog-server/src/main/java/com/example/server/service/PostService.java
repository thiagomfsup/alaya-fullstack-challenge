package com.example.server.service;

import com.example.server.models.Post;
import com.example.server.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {
    List<Post> findAll();

    Optional<Post> findById(UUID uuid);

    Post publish(Post post, UUID user);

    void deleteById(UUID uuid, UUID user);
}
