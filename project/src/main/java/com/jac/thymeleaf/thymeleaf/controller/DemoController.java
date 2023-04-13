package com.jac.thymeleaf.thymeleaf.controller;

import com.jac.thymeleaf.thymeleaf.entity.UserEntity;
import com.jac.thymeleaf.thymeleaf.mapper.MapperHelper;
import com.jac.thymeleaf.thymeleaf.model.UserModel;
import com.jac.thymeleaf.thymeleaf.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class DemoController {
        private final UserService userService;
        private final MapperHelper mapper;
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

        @PostMapping("/register")
        public String register(@ModelAttribute("userModel") @Valid UserModel userModel, BindingResult result, Model model) {
            if (result.hasErrors()) {
//                model.addAttribute("registrationError");
                return "index";
            }

            UserEntity userEntity = mapper.convertUserModeltoEntity(userModel);
            userService.save(userEntity);

//            model.addAttribute("successMessage", "Registration successful. You can log in now.");
//            model.addAttribute("userModel", new UserModel());
            return "index";
        }


}
