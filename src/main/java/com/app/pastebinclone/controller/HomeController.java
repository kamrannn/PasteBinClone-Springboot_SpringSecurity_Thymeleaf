package com.app.pastebinclone.controller;

import com.app.pastebinclone.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    /**
     * This is the controller for the login page, which is rendering the login page
     */
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    /**
     * This is the controller for the login error, whenever the bad credentials will be entered
     * spring security will redirect the user to this controller
     */
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        model.addAttribute("user", new User());
        return "login";
    }
}
