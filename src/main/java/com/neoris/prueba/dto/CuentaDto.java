package com.neoris.prueba.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CuentaDto {
    private Long numeroCuenta;
    private String tipoCuenta;
    private double saldoInicial;
    private String estado;
    private double saldoActual;

    private Long clienteId;
}
