package com.neoris.prueba.service;

import com.neoris.prueba.dto.CuentaDto;

import java.util.List;

public interface CuentaService {
    CuentaDto obtenerCuentaPorNumeroCuenta(Long numeroCuenta);
    CuentaDto crearCuenta(CuentaDto cuentaDto);
    CuentaDto actualizarCuenta(CuentaDto cuentaDto);
    void eliminarCuenta(Long numeroCuenta);

    List<CuentaDto> obtenerCuentas();
}
