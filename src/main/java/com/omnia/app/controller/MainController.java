package com.omnia.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Value("${app.name}")
    String appName;
	
	//TODO
    @GetMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("appName", appName);
        return "index";
    }
}
