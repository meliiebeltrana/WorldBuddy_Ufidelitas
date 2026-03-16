package com.World.service;

import com.World.domain.Pais;
import com.World.repository.PaisRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaisService {

    private final PaisRepository paisRepository;

    public PaisService(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @Transactional(readOnly = true)
    public List<Pais> getPaises() {
        return paisRepository.findAll();
    }
}
