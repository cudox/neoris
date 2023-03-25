package com.neoris.prueba.controller;

import com.neoris.prueba.dto.MovimientoDto;
import com.neoris.prueba.repository.MovimientoRepository;
import com.neoris.prueba.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovimientoController {

    @Autowired
    MovimientoService movimientoService;

    @GetMapping("/movimientos")
    public List<MovimientoDto> getMovimientoDtos() {
       return movimientoService.obtenerListaMovimientos();
    }

    @PostMapping("/movimientos")
    public MovimientoDto crearMovimientoDto(@RequestBody MovimientoDto movimientoDto) {
       return movimientoService.crearMovimiento(movimientoDto);
    }
}
