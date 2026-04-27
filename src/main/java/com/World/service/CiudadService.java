package com.World.service;

import com.World.domain.Ciudad;
import com.World.repository.CiudadRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CiudadService {

    private final CiudadRepository ciudadRepository;

    public CiudadService(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    @Transactional(readOnly = true)
    public List<Ciudad> getCiudades() {
        return ciudadRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Ciudad getCiudad(Ciudad ciudad) {
        return ciudadRepository.findById(ciudad.getIdCiudad()).orElse(null);
    }

    @Transactional
    public void save(Ciudad ciudad) {
        ciudadRepository.save(ciudad);
    }

    @Transactional
    public void delete(Ciudad ciudad) {
        ciudadRepository.delete(ciudad);
    }
}