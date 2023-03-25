package com.neoris.prueba.controller;

import com.neoris.prueba.dto.ClienteDto;
import com.neoris.prueba.dto.MovimientoDto;
import com.neoris.prueba.dto.Reporte;
import com.neoris.prueba.entities.Cuenta;
import com.neoris.prueba.service.ClienteService;
import com.neoris.prueba.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class ReporteController {

    @Autowired
    ClienteService clienteService;
    @Autowired
    MovimientoService movimientoService;

    @GetMapping("/reportes")
    public List<MovimientoDto> generarReporte(@RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam Long clienteId) {
        List<Cuenta> cuentaList = clienteService.obtenerCuentasPorClienteId(clienteId);
        List<MovimientoDto> movimientoDtoList = new ArrayList<>();
        for(Cuenta cuenta : cuentaList){
            movimientoDtoList.addAll(movimientoService.obtenerMovimientosPorNumeroCuentaYFechas(cuenta.getNumeroCuenta(), LocalDate.parse(fechaInicio), LocalDate.parse(fechaFin)));
        }
        return movimientoDtoList;
    }
}
