package com.neoris.prueba.controller;

import com.neoris.prueba.dto.CuentaDto;
import com.neoris.prueba.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CuentaController {

    @Autowired
    CuentaService cuentaService;

    @GetMapping("/cuentas")
    public List<CuentaDto> getCuentas() {
        return cuentaService.obtenerCuentas();
        // Implementar la lógica para obtener todas las cuentas
    }

    @PostMapping("/cuentas")
    public CuentaDto crearCuenta(@RequestBody CuentaDto cuenta) {
        return cuentaService.crearCuenta(cuenta);
        // Implementar la lógica para crear una nueva cuenta
    }

    @GetMapping("/cuentas/{numero_cuenta}")
    public CuentaDto getCuenta(@PathVariable Long numeroCuenta) {
        return cuentaService.obtenerCuentaPorNumeroCuenta(numeroCuenta);
    }

    @PutMapping("/cuentas")
    public CuentaDto actualizarCuenta(@RequestBody CuentaDto cuenta) {
       return cuentaService.actualizarCuenta(cuenta);
    }

    @DeleteMapping("/cuentas/{numero_cuenta}")
    public void eliminarCuenta(@PathVariable Long numeroCuenta) {
        cuentaService.eliminarCuenta(numeroCuenta);
    }
}
