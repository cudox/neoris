package com.neoris.prueba.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movimiento")
@Getter
@Setter
@Builder
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private LocalDate fecha;

    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    @Column
    private BigDecimal valor;

    @Column
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "numero_cuenta", referencedColumnName = "id")
    private Cuenta cuenta;
}