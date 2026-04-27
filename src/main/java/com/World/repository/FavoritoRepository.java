package com.World.repository;

import com.World.domain.Favorito;
import com.World.domain.Ciudad;
import com.World.domain.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    List<Favorito> findByUsuario(Usuario usuario);

    Optional<Favorito> findByUsuarioAndCiudad(Usuario usuario, Ciudad ciudad);
}
