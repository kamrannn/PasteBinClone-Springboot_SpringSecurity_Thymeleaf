package com.app.pastebinclone.controller;

import com.app.pastebinclone.model.GrantType;
import com.app.pastebinclone.model.Paste;
import com.app.pastebinclone.service.PasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paste")
public class PasteController {

    private final PasteService pasteService;

    @Autowired
    public PasteController(PasteService pasteService) {
        this.pasteService = pasteService;
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
}
