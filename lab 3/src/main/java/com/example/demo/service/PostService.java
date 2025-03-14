package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// PostService.java
@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() { return postRepository.findAll(); }

    public Optional<Post> getPostById(Long id) { return postRepository.findById(id); }

    public Post createPost(Post post) { return postRepository.save(post); }

    public void deletePost(Long id) { postRepository.deleteById(id); }
}

