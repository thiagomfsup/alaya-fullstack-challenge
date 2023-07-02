package com.example.server.service;

import com.example.server.exception.PermissionException;
import com.example.server.exception.ResourceNotFoundException;
import com.example.server.models.Post;
import com.example.server.repository.PostRepository;
import com.github.slugify.Slugify;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "dateAdded"));
    }

    @Override
    public Optional<Post> findById(UUID uuid) {
        return postRepository.findById(uuid);
    }

    @Override
    public Post publish(Post post, UUID userId) {
        // TODO sanitize postDTO

        post.setCuid(UUID.randomUUID());
        post.setSlug(Slugify.builder().build().slugify(post.getTitle()));
        post.setCreatedBy(userId);

        return postRepository.save(post);
    }

    @Override
    public void deleteById(UUID uuid, UUID userId) {
        final Optional<Post> optPost = postRepository.findById(uuid);

        if (!optPost.isPresent())
            throw new ResourceNotFoundException("Post Not Found");

        Post post = optPost.get();

        if (!userId.equals(post.getCreatedBy()))
            throw new PermissionException("User cannot delete this Post");

        postRepository.delete(post);
    }
}
