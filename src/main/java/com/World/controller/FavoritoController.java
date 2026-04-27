package com.World.controller;

import com.World.domain.Ciudad;
import com.World.domain.Favorito;
import com.World.domain.Usuario;
import com.World.repository.CiudadRepository;
import com.World.repository.FavoritoRepository;
import com.World.repository.UsuarioRepository;
import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FavoritoController {

    private final FavoritoRepository favoritoRepository;
    private final CiudadRepository ciudadRepository;
    private final UsuarioRepository usuarioRepository;

    public FavoritoController(FavoritoRepository favoritoRepository,
                              CiudadRepository ciudadRepository,
                              UsuarioRepository usuarioRepository) {
        this.favoritoRepository = favoritoRepository;
        this.ciudadRepository = ciudadRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/favorito/agregar/{idCiudad}")
    public String agregarFavorito(@PathVariable Integer idCiudad, Principal principal) {

        Usuario usuario = usuarioRepository.findByCorreo(principal.getName()).orElse(null);
        Ciudad ciudad = ciudadRepository.findById(idCiudad).orElse(null);

        if (usuario == null || ciudad == null) {
            return "redirect:/ciudad/listado?errorFavorito=true";
        }

        var existe = favoritoRepository.findByUsuarioAndCiudad(usuario, ciudad);

        if (existe.isPresent()) {
            return "redirect:/ciudad/listado?yaExiste=true";
        }

        Favorito favorito = new Favorito();
        favorito.setUsuario(usuario);
        favorito.setCiudad(ciudad);

        favoritoRepository.save(favorito);

        return "redirect:/favorito/listado?agregado=true";
    }

    @GetMapping("/favorito/listado")
    public String listadoFavoritos(Model model, Principal principal) {

        Usuario usuario = usuarioRepository.findByCorreo(principal.getName()).orElse(null);

        model.addAttribute("favoritos", favoritoRepository.findByUsuario(usuario));

        return "favorito/listado";
    }

    @GetMapping("/favorito/eliminar/{idFavorito}")
    public String eliminarFavorito(@PathVariable Long idFavorito) {
        favoritoRepository.deleteById(idFavorito);
        return "redirect:/favorito/listado?eliminado=true";
    }
}
