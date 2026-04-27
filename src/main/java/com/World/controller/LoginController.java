package com.World.controller;

import com.World.domain.Usuario;
import com.World.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/registro")
    public String registro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login/registro";
    }

    @PostMapping("/guardarRegistro")
    public String guardarRegistro(Usuario usuario, Model model) {

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            model.addAttribute("error", "Este correo ya está registrado.");
            return "login/registro";
        }

        usuario.setRol("USER");
        usuarioRepository.save(usuario);

        return "redirect:/login?registro=true";
    }
}