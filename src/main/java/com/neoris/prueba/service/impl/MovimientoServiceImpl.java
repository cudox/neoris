package com.neoris.prueba.service.impl;

import com.neoris.prueba.dto.MovimientoDto;
import com.neoris.prueba.entities.Cuenta;
import com.neoris.prueba.entities.Movimiento;
import com.neoris.prueba.opciones.TipoMovimiento;
import com.neoris.prueba.repository.CuentaRepository;
import com.neoris.prueba.repository.MovimientoRepository;
import com.neoris.prueba.service.MovimientoService;
import com.neoris.prueba.utils.Utils;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovimientoServiceImpl implements MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public List<MovimientoDto> obtenerMovimientosPorNumeroCuenta(Long numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new NotFoundException("Cuenta no encontrada"));
        List<Movimiento> movimientos = movimientoRepository.findByCuenta(cuenta);
        List<MovimientoDto> movimientoDtoList = movimientos.stream()
                .map(movimiento -> Utils.movimientoToDTO(movimiento))
                .collect(Collectors.toList());
        return movimientoDtoList;
    }

    @Override
    public List<MovimientoDto> obtenerMovimientosPorNumeroCuentaYFechas(Long numeroCuenta, LocalDate fechaInicial, LocalDate fechaFinal) {
        Cuenta cuenta = getCuenta(numeroCuenta);
        List<Movimiento> movimientos = movimientoRepository.findByCuentaAndFechaBetween(cuenta, fechaInicial, fechaFinal);
        List<MovimientoDto> movimientoDtoList = movimientos.stream()
                .map(movimiento -> Utils.movimientoToDTO(movimiento))
                .collect(Collectors.toList());
        return movimientoDtoList;
    }

    @Override
    public MovimientoDto crearMovimiento(MovimientoDto movimientoDto) {
        Cuenta cuenta = getCuenta(movimientoDto.getNumeroCuenta());
        Movimiento movimiento = Movimiento.builder()
                .cuenta(cuenta)
                .fecha(movimientoDto.getFecha())
                .tipoMovimiento(movimientoDto.getTipoMovimiento())
                .valor(BigDecimal.valueOf(movimientoDto.getValor()))
                .build();
        movimiento.setCuenta(cuenta); // establecer la cuenta asociada al movimiento
        // verificar si el tipo de movimiento es crédito o débito y actualizar el saldo de la cuenta en consecuencia
        BigDecimal valor = BigDecimal.valueOf(movimientoDto.getValor());
        BigDecimal saldoActual = cuenta.getSaldoActual();
        if (TipoMovimiento.CREDITO.equals(movimiento.getTipoMovimiento())) {
            cuenta.setSaldoActual(saldoActual.add(valor));
        } else if (TipoMovimiento.DEBITO.equals(movimiento.getTipoMovimiento())) {
            if (cuenta.getSaldoActual().compareTo(movimiento.getValor()) < 0) { // verificar si hay suficiente saldo en la cuenta
                throw new BadRequestException("Saldo no disponible");
            }
            cuenta.setSaldoActual(saldoActual.subtract(valor));
        }
        cuentaRepository.save(cuenta);
        movimiento.setSaldo(cuenta.getSaldoActual());
        movimientoRepository.save(movimiento);

        return Utils.movimientoToDTO(movimiento);
    }

    @Override
    public List<MovimientoDto> obtenerListaMovimientos() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        List<MovimientoDto> movimientoDtoList = movimientos.stream()
                .map(movimiento -> Utils.movimientoToDTO(movimiento))
                .collect(Collectors.toList());
        return movimientoDtoList;

    }

    private Cuenta getCuenta(Long numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new NotFoundException("Cuenta no encontrada"));
        return cuenta;
    }
}
