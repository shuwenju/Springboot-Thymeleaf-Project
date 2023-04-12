package com.jac.thymeleaf.thymeleaf.controller;

import com.jac.thymeleaf.thymeleaf.model.Post;
import com.jac.thymeleaf.thymeleaf.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("")
public class PostController
    {
        private final PostService postService;

        @Autowired
        public PostController(PostService postService) {
            this.postService = postService;
        }

        @GetMapping("/newsfeed")
        public String profilePosts(Model theModel){
            List<Post> thePosts = postService.getAllPosts();

            theModel.addAttribute("posts", thePosts);
            return "newsfeed";
        }


    }
