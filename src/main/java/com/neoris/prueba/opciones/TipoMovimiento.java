package com.neoris.prueba.opciones;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoMovimiento {
    DEBITO("Débito"), CREDITO("Crédito");

    private final String descripcion;
}
