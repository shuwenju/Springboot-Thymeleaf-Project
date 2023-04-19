package com.jac.thymeleaf.thymeleaf.controller;

import ch.qos.logback.core.model.conditional.ThenModel;
import com.jac.thymeleaf.thymeleaf.entity.PostEntity;
import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import com.jac.thymeleaf.thymeleaf.mapper.MapperHelper;
import com.jac.thymeleaf.thymeleaf.model.CommentModel;
import com.jac.thymeleaf.thymeleaf.model.LoginFormModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;
import com.jac.thymeleaf.thymeleaf.model.UserModel;
import com.jac.thymeleaf.thymeleaf.repository.PostRepository;
import com.jac.thymeleaf.thymeleaf.repository.UserRepository;
import com.jac.thymeleaf.thymeleaf.service.MediaService;
import com.jac.thymeleaf.thymeleaf.service.UserService;

import com.jac.thymeleaf.thymeleaf.view.CommentView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/social")
public class DemoController {
        private final UserService userService;


        private final MediaService mediaService;
        private final MapperHelper mapper;
        private final PostRepository postRepository;

        @Autowired
        private UserRepository userRepository;
        @Autowired
        public DemoController(UserService userService, MediaService mediaService, MapperHelper mapper, PostRepository postRepository) {
            this.userService = userService;
            this.mediaService = mediaService;
            this.mapper = mapper;
            this.postRepository = postRepository;
        }

        @GetMapping
            public String index(Model model) {
            model.addAttribute("userModel", new UserModel());

            return "index";
        }

        @GetMapping("/register")
        public String showRegistrationForm(Model model) {
            model.addAttribute("userModel", new UserModel());
            return "index";
        }

        @PostMapping("/register")
        public String register(@ModelAttribute("userModel") @Valid UserModel userModel, BindingResult result, Model model) {
            if (result.hasErrors()) {
                return "index"; //return "redirect:/social"; this doesn't display the error messages TODO need to display error messages
            }

            // Check if the email already exists in the database
            Optional<UserEntity> existingUser = userRepository.findByEmail(userModel.getEmail());
            if (existingUser.isPresent()) {
                result.rejectValue("email", "error.email", "This email is already taken.");
                return "index";
            }

            // Check if the username already exists in the database
            Optional<UserEntity> existingUser2 = userRepository.findByUsername(userModel.getUsername());
            if (existingUser2.isPresent()) {
                result.rejectValue("username", "error.username", "This username is already taken.");
                return "index";
            }


            UserEntity userEntity = mapper.convertUserModeltoEntity(userModel);
            userService.save(userEntity);

            return "login"; //TODO need to redirect to login form

        }

        @GetMapping("/login")
        public String showLoginPage(Model model) {
            model.addAttribute("userModel", new UserModel());
            return "login"; //TODO need to redirect to login form
        }

        @PostMapping("/login")
        public String processLoginForm(@Valid @ModelAttribute("userModel") UserModel userModel,
                                       BindingResult bindingResult, Model model, HttpServletRequest request) {
            if (bindingResult.hasErrors()) {
                return "login"; //TODO need to redirect to login form and display error messages
            }

            Optional<UserEntity> optionalUser = userRepository.findByEmail(userModel.getEmail());

            if (!optionalUser.isPresent()) {
                bindingResult.rejectValue("email", "error.email", "Invalid email.");
                return "login"; //TODO need to redirect to login form
            }

            UserEntity user = optionalUser.get();

            if (!user.getPassword().equals(userModel.getPassword())) {
                bindingResult.rejectValue("password", "error.password", "Wrong password.");
                return "login"; //TODO need to redirect to login form
            }

            // Login successful, set user in session and redirect to dashboard
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("user", user);

            session.setAttribute("fname", user.getFirstName());
            session.setAttribute("lname", user.getLastName());
            session.setAttribute("username", user.getUsername());
            session.setAttribute("sex", user.getSex());

            System.out.println(session.getAttribute("user"));
            return "redirect:/social/newsfeed";
        }


    @GetMapping("/newsfeed")
    public String profilePosts(Model theModel, HttpSession session){
        UserEntity userEntity = (UserEntity) session.getAttribute("user");

        if (userEntity == null) {
            return "redirect:/social"; // Redirect to the index page
        }

        UserModel userModel = mapper.convertUserEntitytoModel(userEntity);
        theModel.addAttribute("user", userModel);

        List<PostModel> postList = mediaService.getAllPosts();
        Map<Long, List<CommentModel>> commentByPostId = new HashMap<>();
        int totalComments = 0;
        for(PostModel post : postList){
            List<CommentModel> comments = mediaService.getAllCommentByPostId(post.getId());
            commentByPostId.put(post.getId(), comments);
            totalComments += comments.size();
        }
        theModel.addAttribute("postList", postList);
        theModel.addAttribute("commentsByPost", commentByPostId);
        theModel.addAttribute("totalComments", totalComments);
        return "newsfeed";
    }

    @PostMapping("/addcomments")
    public String addComment( @RequestParam("content") String commentContent,
                              @RequestParam("postId") Long postId,
                              HttpServletRequest request){
        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        if (userEntity == null) {
            return "redirect:/social"; // Redirect to the index page
        }
        UserModel userModel = mapper.convertUserEntitytoModel(userEntity);
        PostModel post = mediaService.findPostById(postId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        CommentModel newComment = CommentModel.builder()
                .content(commentContent)
                .createdAt(localDateTime)
                .user(userModel)
                .post(post)
                .build();
        mediaService.saveComment(newComment);
        return "redirect:newsfeed";

    }

    @PostMapping("/post")
    public String postPost(@RequestParam("postContent") String postContent,
                           HttpServletRequest request){
        HttpSession session = request.getSession();
        UserEntity userEntity = (UserEntity) session.getAttribute("user");
        if (userEntity == null) {
            return "redirect:/social"; // Redirect to the index page
        }
        UserModel user = mapper.convertUserEntitytoModel(userEntity);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        PostModel newPost = PostModel.builder()
                .content(postContent)
                .createdAt(localDateTime)
                .user(user).build();
        mediaService.savePost(newPost);
        return "redirect:newsfeed";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/social";
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("user");

        if (user == null) {
            return "redirect:/social"; // Redirect to the index page if user is not logged in
        }

        model.addAttribute("user", user);
        List<PostEntity> posts = postRepository.findAllByUserId(user.getId()).orElse(new ArrayList<>());
        model.addAttribute("posts", posts);
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid UserModel userModel, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            UserEntity user = (UserEntity) session.getAttribute("user");
            model.addAttribute("user", user);
            return "profile";
        }

        UserEntity user = (UserEntity) session.getAttribute("user");
        if (user == null) {
            return "redirect:/social"; // Redirect to the index page if user is not logged in
        }

        // Update user information
        user.setUsername(userModel.getUsername());
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());

        // Save the updated user information to the database
        userRepository.save(user);

        return "redirect:/social/profile";
    }
}


