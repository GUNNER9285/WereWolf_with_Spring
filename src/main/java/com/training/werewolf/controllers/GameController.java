package com.training.werewolf.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/")
public class GameController {
    @GetMapping(value = "")
    public String index() throws Exception {
        return "sample/index";
    }

}

