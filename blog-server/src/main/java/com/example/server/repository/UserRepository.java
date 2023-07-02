package com.example.server.repository;

import com.example.server.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {
    @Query("{username: '?0'}")
    Optional<User> findByUsername(String username);

    @Query("{email: '?0'}")
    Optional<User> findByEmail(String email);
}
