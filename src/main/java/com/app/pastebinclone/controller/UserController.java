package com.app.pastebinclone.controller;

import com.app.pastebinclone.model.GrantType;
import com.app.pastebinclone.model.Paste;
import com.app.pastebinclone.model.User;
import com.app.pastebinclone.service.PasteService;
import com.app.pastebinclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PasteService pasteService;

    @Autowired
    public UserController(UserService userService, PasteService pasteService) {
        this.userService = userService;
        this.pasteService = pasteService;
    }


    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        } else if (userService.getUserByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("isUsernamePresent", true);
            return "register";

        } else if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("isEmailPresent", true);
            return "register";
        } else {
            userService.save(user);
            model.addAttribute("success", true);
            model.addAttribute("user", new User());//To reset the form
            return "register";
        }
    }

    @GetMapping("/home")
    public String userHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userService.getUserByUsername(username);
        model.addAttribute("pasteList", user.get().getPasteList());
        return "user-home";
    }

    @GetMapping("/create/paste")
    public String createPaste(Model model) {
        model.addAttribute("paste", new Paste());
        return "create-paste";
    }

    @PostMapping("/create/paste")
    public String createPaste(@ModelAttribute Paste paste, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            Optional<User> user = userService.getUserByUsername(username);
            if (user.isPresent()) {
                Paste savedPaste = pasteService.save(paste);
                user.get().getPasteList().add(savedPaste);
                userService.save(user.get());
                model.addAttribute("success", true);
                model.addAttribute("paste", new Paste());
                if (savedPaste.getAuthorizationType().equals(GrantType.PRIVATE)) {
                    return "redirect:/paste/access/" + savedPaste.getId();
                }
            }
        }
        return "create-paste";
    }
}
