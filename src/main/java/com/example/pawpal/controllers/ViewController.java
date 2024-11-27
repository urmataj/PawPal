package com.example.pawpal.controllers;

import com.example.pawpal.entities.PostPetEntity;
import com.example.pawpal.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String index(Model model) {
        List<PostPetEntity> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "index"; // Renders index.html
    }
}

