package com.neoris.prueba.utils;

import com.neoris.prueba.dto.ClienteDto;
import com.neoris.prueba.dto.CuentaDto;
import com.neoris.prueba.dto.MovimientoDto;
import com.neoris.prueba.entities.Cliente;
import com.neoris.prueba.entities.Cuenta;
import com.neoris.prueba.entities.Movimiento;

public class Utils {
    public static ClienteDto clienteToDTO(Cliente cliente) {
        return ClienteDto.builder()
                .clienteId(cliente.getClienteId())
                .edad(cliente.getEdad())
                .nombre(cliente.getNombre())
                .estado(cliente.getEstado())
                .genero(cliente.getGenero())
                .direccion(cliente.getDireccion())
                .identificacion(cliente.getIdentificacion())
                .telefono(cliente.getTelefono())
                .build();
    }

    public static CuentaDto cuentaToDTO(Cuenta cuenta) {
        return CuentaDto.builder()
                .numeroCuenta(cuenta.getNumeroCuenta())
                .tipoCuenta(cuenta.getTipoCuenta())
                .clienteId(cuenta.getCliente().getClienteId())
                .estado(cuenta.getEstado())
                .saldoInicial(cuenta.getSaldoInicial().doubleValue())
                .saldoActual(cuenta.getSaldoActual().doubleValue())
                .build();
    }

    public static MovimientoDto movimientoToDTO(Movimiento movimiento) {
        return MovimientoDto.builder()
                .fecha(movimiento.getFecha())
                .numeroCuenta(movimiento.getCuenta().getNumeroCuenta())
                .saldo(movimiento.getSaldo().doubleValue())
                .tipoMovimiento(movimiento.getTipoMovimiento())
                .valor(movimiento.getValor().doubleValue())
                .build();
    }
}
