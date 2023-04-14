package com.jac.thymeleaf.thymeleaf.controller;

import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import com.jac.thymeleaf.thymeleaf.mapper.MapperHelper;
import com.jac.thymeleaf.thymeleaf.model.CommentModel;
import com.jac.thymeleaf.thymeleaf.model.LoginFormModel;
import com.jac.thymeleaf.thymeleaf.model.PostModel;
import com.jac.thymeleaf.thymeleaf.model.UserModel;
import com.jac.thymeleaf.thymeleaf.repository.UserRepository;
import com.jac.thymeleaf.thymeleaf.service.MediaService;
import com.jac.thymeleaf.thymeleaf.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/social")
public class DemoController {
        private final UserService userService;


        private final MediaService mediaService;
        private final MapperHelper mapper;

        @Autowired
        private UserRepository userRepository;
        @Autowired
        public DemoController(UserService userService, MediaService mediaService, MapperHelper mapper) {
            this.userService = userService;
            this.mediaService = mediaService;
            this.mapper = mapper;
        }

        @GetMapping
            public String index(Model model) {
            model.addAttribute("userModel", new UserModel());
            return "index";
        }

        @GetMapping("/register")
        public String showRegistrationForm(Model model) {
            model.addAttribute("userModel", new UserModel());
            return "register";
        }

        @PostMapping("/register")
        public String register(@ModelAttribute("userModel") @Valid UserModel userModel, BindingResult result, Model model) {
            if (result.hasErrors()) {
//                model.addAttribute("registrationError");
                return "register";
            }else {

                UserEntity userEntity = mapper.convertUserModeltoEntity(userModel);
                userService.save(userEntity);
            }

//            model.addAttribute("successMessage", "Registration successful. You can log in now.");
//            model.addAttribute("userModel", new UserModel());
            return "login";

        }

        @GetMapping("/login")
        public String showLoginPage(Model model) {
            model.addAttribute("loginForm", new LoginFormModel());
            return "login";
        }

        @PostMapping("/login")
        public String processLoginForm(@Valid @ModelAttribute("loginForm") LoginFormModel loginForm,
                                       BindingResult bindingResult, Model model, HttpServletRequest request) {
            if (bindingResult.hasErrors()) {
                return "login";
            }

            Optional<UserEntity> optionalUser = userRepository.findByEmail(loginForm.getEmail());

            if (!optionalUser.isPresent()) {
                model.addAttribute("errorMessage", "Invalid email or password");
                return "login";
            }

            UserEntity user = optionalUser.get();

            if (!user.getPassword().equals(loginForm.getPassword())) {
                model.addAttribute("errorMessage", "Invalid email or password");
                return "login";
            }

            // Login successful, set user in session and redirect to dashboard
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/newsfeed";
        }


    @GetMapping("/newsfeed")
    public String profilePosts(Model theModel){
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
}
