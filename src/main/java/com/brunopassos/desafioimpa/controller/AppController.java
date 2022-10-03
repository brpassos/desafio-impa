package com.brunopassos.desafioimpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/")
    public String home() {
        return "Desafio IMPA - API para gerenciamento de tarefas";
    }

}
