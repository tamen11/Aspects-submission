package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        if (post.getUser() == null || post.getUser().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> userOptional = userRepository.findById(post.getUser().getId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userOptional.get();

        // Maintain both sides of the relationship
        post.setUser(user);
        user.getPosts().add(post);

        return ResponseEntity.ok(postService.createPost(post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        Optional<Post> existingPostOpt = postService.getPostById(id);
        if (existingPostOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Post existingPost = existingPostOpt.get();

        // Validate user
        if (updatedPost.getUser() == null || updatedPost.getUser().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<User> userOptional = userRepository.findById(updatedPost.getUser().getId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Update the post fields
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setContent(updatedPost.getContent());
        existingPost.setUser(userOptional.get());

        return ResponseEntity.ok(postService.createPost(existingPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        if (postService.getPostById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}