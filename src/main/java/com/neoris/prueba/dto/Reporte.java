package com.neoris.prueba.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reporte {
    private List<CuentaDto> cuentas;
    private double totalDebitos;
    private double totalCreditos;
}
