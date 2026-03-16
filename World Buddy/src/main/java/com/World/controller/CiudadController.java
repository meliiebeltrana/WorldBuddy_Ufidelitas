package com.World.controller;

import com.World.domain.Ciudad;
import com.World.service.CiudadService;
import com.World.service.PaisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ciudad")
public class CiudadController {

    private final CiudadService ciudadService;
    private final PaisService paisService;

    public CiudadController(CiudadService ciudadService, PaisService paisService) {
        this.ciudadService = ciudadService;
        this.paisService = paisService;
    }

    @GetMapping("/listado")
    public String listado(Model model) {
        var ciudades = ciudadService.getCiudades();
        var paises = paisService.getPaises();

        model.addAttribute("ciudades", ciudades);
        model.addAttribute("paises", paises);
        model.addAttribute("totalCiudades", ciudades.size());
        model.addAttribute("ciudad", new Ciudad());

        return "/ciudad/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Ciudad ciudad) {
        ciudadService.save(ciudad);
        return "redirect:/ciudad/listado";
    }

    @GetMapping("/eliminar/{idCiudad}")
    public String eliminar(@PathVariable("idCiudad") Integer idCiudad) {
        Ciudad ciudad = new Ciudad();
        ciudad.setIdCiudad(idCiudad);
        ciudadService.delete(ciudad);
        return "redirect:/ciudad/listado";
    }

    @GetMapping("/modificar/{idCiudad}")
    public String modificar(@PathVariable("idCiudad") Integer idCiudad, Model model) {
        Ciudad ciudad = ciudadService.getCiudad(new Ciudad(idCiudad));
        var ciudades = ciudadService.getCiudades();
        var paises = paisService.getPaises();

        model.addAttribute("ciudad", ciudad);
        model.addAttribute("ciudades", ciudades);
        model.addAttribute("paises", paises);
        model.addAttribute("totalCiudades", ciudades.size());

        return "/ciudad/listado";
    }
}
