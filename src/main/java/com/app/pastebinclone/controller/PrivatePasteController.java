package com.app.pastebinclone.controller;

import com.app.pastebinclone.model.PrivatePaste;
import com.app.pastebinclone.model.User;
import com.app.pastebinclone.service.PrivatePasteService;
import com.app.pastebinclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/privatePaste")
public class PrivatePasteController {
    private final PrivatePasteService privatePasteService;
    private final UserService userService;

    @Autowired
    public PrivatePasteController(PrivatePasteService privatePasteService, UserService userService) {
        this.privatePasteService = privatePasteService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String usersPrivatePasteAccess(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            List<PrivatePaste> pasteList = privatePasteService.privatePasteList(user.get().getEmail());
            model.addAttribute("pasteList", pasteList);
        } else {
            model.addAttribute("userNotFound", true);
        }

        return "private-paste";
    }

    @GetMapping("/{pasteId}")
    public String getPasteById(@PathVariable(name = "pasteId") Integer pasteId, Model model) {
        model.addAttribute("paste", privatePasteService.getPasteById(pasteId));
        return "private-paste-information";
    }
}
