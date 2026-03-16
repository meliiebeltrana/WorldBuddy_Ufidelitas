package com.World.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ciudad")
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;

    public Ciudad(Integer idCiudad) {
        this.idCiudad = idCiudad;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Integer idCiudad;

    @Column(name = "nombre_ciudad", length = 100, nullable = false)
    private String nombreCiudad;

    @Column(length = 10, nullable = false)
    private String hora;

    @Column(length = 50, nullable = false)
    private String clima;

    @Column(nullable = false)
    private Double temperatura;

    @Column(name = "dia_noche", length = 10, nullable = false)
    private String diaNoche;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais pais = new Pais();
}
