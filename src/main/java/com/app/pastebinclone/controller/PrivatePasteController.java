package com.app.pastebinclone.controller;

import com.app.pastebinclone.service.PrivatePasteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/privatePaste")
public class PrivatePasteController {
    private final PrivatePasteService privatePasteService;

    @Autowired
    public PrivatePasteController(PrivatePasteService privatePasteService) {
        this.privatePasteService = privatePasteService;
    }
}
