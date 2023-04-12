package com.jac.thymeleaf.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController
    {
        @GetMapping("/")
        public String landingpage(Model theModel) {
            return "landingpage";
        }
    }
