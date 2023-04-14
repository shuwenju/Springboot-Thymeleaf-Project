package com.jac.thymeleaf.thymeleaf.controller;

import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import com.jac.thymeleaf.thymeleaf.mapper.MapperHelper;
import com.jac.thymeleaf.thymeleaf.model.LoginFormModel;
import com.jac.thymeleaf.thymeleaf.model.UserModel;
import com.jac.thymeleaf.thymeleaf.repository.UserRepository;
import com.jac.thymeleaf.thymeleaf.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/social")
public class DemoController {
        private final UserService userService;
        private final MapperHelper mapper;

        @Autowired
        private UserRepository userRepository;
        @Autowired
        public DemoController(UserService userService, MapperHelper mapper) {
            this.userService = userService;
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
            }

            UserEntity userEntity = mapper.convertUserModeltoEntity(userModel);
            userService.save(userEntity);

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


}
