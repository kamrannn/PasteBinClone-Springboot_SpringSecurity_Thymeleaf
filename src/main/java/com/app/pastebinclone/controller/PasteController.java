package com.app.pastebinclone.controller;

import com.app.pastebinclone.model.GrantType;
import com.app.pastebinclone.model.Paste;
import com.app.pastebinclone.model.PrivatePaste;
import com.app.pastebinclone.model.User;
import com.app.pastebinclone.service.PasteService;
import com.app.pastebinclone.service.PrivatePasteService;
import com.app.pastebinclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/paste")
public class PasteController {

    private final PasteService pasteService;
    private final UserService userService;
    private final PrivatePasteService privatePasteService;

    @Autowired
    public PasteController(PasteService pasteService, UserService userService, PrivatePasteService privatePasteService) {
        this.pasteService = pasteService;
        this.userService = userService;
        this.privatePasteService = privatePasteService;
    }

    @GetMapping("/{pasteId}")
    public String getPasteById(@PathVariable(name = "pasteId") Integer pasteId, Model model) {
        model.addAttribute("paste", pasteService.getPasteById(pasteId));
        return "paste-full-information";
    }

    @GetMapping("/public")
    public String getPublicPaste(Model model) {
        model.addAttribute("pasteList", pasteService.getPasteByGrantType(GrantType.PUBLIC));
        return "public-paste";
    }

    @GetMapping("/access/{pasteId}")
    public String pasteAccess(Model model, @PathVariable(name = "pasteId") Integer pasteId) {
        model.addAttribute("pasteId", pasteId);
        return "paste-access-page";
    }

    @PostMapping("/access/{pasteId}")
    public String pasteAccessSubmission(@RequestParam(name = "email") String userEmail, @PathVariable(name = "pasteId") Integer pasteId, Model model) {

        //Searching for the existing paste
        Paste existingPaste = pasteService.getPasteById(pasteId);
        PrivatePaste privatePaste = new PrivatePaste();
        privatePaste.setPrivatePasteName(existingPaste.getName());
        privatePaste.setPrivatePasteDescription(existingPaste.getDescription());
        Optional<User> user = userService.getUserByEmail(userEmail);
        if (!user.isPresent()) {
            model.addAttribute("userNotFound", true);
            return "paste-access-page";
        }
        privatePaste.getUserList().add(user.get());

        privatePasteService.save(privatePaste);

        model.addAttribute("success", true);
        model.addAttribute("pasteId", pasteId);
        return "paste-access-page";
    }
}
