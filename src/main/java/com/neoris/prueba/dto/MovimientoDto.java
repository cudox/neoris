package com.neoris.prueba.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimientoDto {

    private Long numeroCuenta;
    private LocalDate fecha;
    private String tipoMovimiento;
    private double valor;
    private double saldo;

    // Constructor, getters y setters
}