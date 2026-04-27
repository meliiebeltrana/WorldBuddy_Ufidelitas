package com.World.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(Model model, Principal principal) {

        String correo = principal.getName();
        String nombre = correo.contains("@") ? correo.substring(0, correo.indexOf("@")) : correo;

        model.addAttribute("nombreUsuario", nombre);

        return "index";
    }
}