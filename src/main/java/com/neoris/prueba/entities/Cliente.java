package com.neoris.prueba.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cliente_id")
    private Long clienteId;

    @Column
    private String nombre;
    @Column
    private String genero;
    @Column
    private int edad;
    @Column
    private String identificacion;
    @Column
    private String direccion;
    @Column
    private String telefono;

    @Column
    private String contrasena;
    @Column
    private String estado;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cuenta> cuentaList = new ArrayList<>();
}
