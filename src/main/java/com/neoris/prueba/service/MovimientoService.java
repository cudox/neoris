package com.neoris.prueba.service;

import com.neoris.prueba.dto.MovimientoDto;

import java.time.LocalDate;
import java.util.List;

public interface MovimientoService {
    List<MovimientoDto> obtenerMovimientosPorNumeroCuenta(Long numeroCuenta);
    List<MovimientoDto> obtenerMovimientosPorNumeroCuentaYFechas(Long numeroCuenta, LocalDate fechaInicial, LocalDate fechaFinal);
    MovimientoDto crearMovimiento(MovimientoDto movimientoDto);

    List<MovimientoDto> obtenerListaMovimientos();
}
