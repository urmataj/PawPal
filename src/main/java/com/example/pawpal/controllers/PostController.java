package com.example.pawpal.controllers;

import com.example.pawpal.Type;
import com.example.pawpal.dto.PostUpdateDto;
import com.example.pawpal.dto.SuccessDto;
import com.example.pawpal.entities.PostPetEntity;
import com.example.pawpal.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("get-all")
    public ResponseEntity<List<PostPetEntity>> getAllPosts() {
        return ResponseEntity.ok(postRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostPetEntity> getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<PostPetEntity>> getPostsByType(@PathVariable Type type) {
        return ResponseEntity.ok(postRepository.findByType(type));
    }

    @PostMapping("create")
    public ResponseEntity<PostPetEntity> createPost(@RequestBody PostPetEntity post) {
        PostPetEntity createdPost = postRepository.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PostPetEntity> updatePost(@RequestBody PostUpdateDto post, @PathVariable("id") Long id) {
        PostPetEntity updatedPost = postRepository.findById(id).get();
        if (post.getTitle() != null) {
            updatedPost.setTitle(post.getTitle());
        }
        if (post.getContent() != null) {
            updatedPost.setContent(post.getContent());
        }
        if (post.getType() != null) {
            updatedPost.setType(post.getType());
        }
        postRepository.save(updatedPost);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("delete/{id}")
    public SuccessDto deletePost(@PathVariable("id") Long id) {
        postRepository.deleteById(id);
        return new SuccessDto();
    }
}
