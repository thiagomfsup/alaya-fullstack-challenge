package com.example.server.controller;

import com.example.server.controller.dto.AddPostRequestDTO;
import com.example.server.controller.dto.AddPostResponseDTO;
import com.example.server.controller.dto.ListOfPostsResponseDTO;
import com.example.server.models.Post;
import com.example.server.models.User;
import com.example.server.repository.PostRepository;
import com.example.server.service.PostService;
import com.github.slugify.Slugify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin
public class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ListOfPostsResponseDTO getPosts() {
        return ListOfPostsResponseDTO.of(postService.findAll());
    }

    @GetMapping(path = "/{uuid}")
    public ResponseEntity<Post> getPostById(@PathVariable UUID uuid) {
        LOGGER.info("Searching for Post ID {}", uuid);
        return ResponseEntity.of(postService.findById(uuid));
    }

    @PostMapping
    public AddPostResponseDTO addPost(@RequestBody AddPostRequestDTO postDTO, Principal principal) {
        final Post savedPost = postService.publish(postDTO.post(), jwtPrincipalToUserUUID((JwtAuthenticationToken) principal));
        LOGGER.info("Article from {} ('{}') has been published on {}",
                savedPost.getName(), savedPost.getTitle(), savedPost.getDateAdded());

        return new AddPostResponseDTO(savedPost);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<?> deleteById(@PathVariable UUID uuid, Principal principal) {
        LOGGER.info("Deleting published post with id {}", uuid);
        postService.deleteById(uuid, jwtPrincipalToUserUUID((JwtAuthenticationToken) principal));

        return ResponseEntity.ok().build();
    }

    private UUID jwtPrincipalToUserUUID(JwtAuthenticationToken token) {
        final Jwt principal = (Jwt) token.getPrincipal();

        return UUID.fromString(principal.getSubject());
    }
}
