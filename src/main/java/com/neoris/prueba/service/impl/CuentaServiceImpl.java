package com.neoris.prueba.service.impl;

import com.neoris.prueba.dto.ClienteDto;
import com.neoris.prueba.dto.CuentaDto;
import com.neoris.prueba.entities.Cliente;
import com.neoris.prueba.entities.Cuenta;
import com.neoris.prueba.repository.ClienteRepository;
import com.neoris.prueba.repository.CuentaRepository;
import com.neoris.prueba.service.CuentaService;
import com.neoris.prueba.utils.Utils;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public CuentaDto obtenerCuentaPorNumeroCuenta(Long numeroCuenta) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new NotFoundException("Cuenta no encontrada"));
        return Utils.cuentaToDTO(cuenta);
    }

    @Override
    public CuentaDto crearCuenta(CuentaDto cuentaDto) {
        Cliente cliente = clienteRepository.findById(cuentaDto.getClienteId())
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));
        Cuenta cuenta = Cuenta.builder()
                .tipoCuenta(cuentaDto.getTipoCuenta())
                .saldoInicial(BigDecimal.valueOf(cuentaDto.getSaldoInicial()))
                .numeroCuenta(cuentaDto.getNumeroCuenta())
                .tipoCuenta(cuentaDto.getTipoCuenta())
                .estado(cuentaDto.getEstado())
                .cliente(cliente)
                .build();
        cuenta.setSaldoInicial(BigDecimal.valueOf(cuentaDto.getSaldoInicial())); // establecer el saldo actual al saldo inicial
        cuenta.setSaldoActual(BigDecimal.valueOf(cuentaDto.getSaldoInicial()));
        cuenta.setCliente(cliente);
        cuenta = cuentaRepository.save(cuenta);
        return Utils.cuentaToDTO(cuenta);
    }

    @Override
    public CuentaDto actualizarCuenta(CuentaDto cuentaDto) {
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(cuentaDto.getNumeroCuenta())
                .orElseThrow(() -> new NotFoundException("Cuenta no encontrada"));
        cuenta.setTipoCuenta(cuentaDto.getTipoCuenta());
        cuenta.setEstado(cuentaDto.getEstado());
        cuenta = cuentaRepository.save(cuenta);
        return Utils.cuentaToDTO(cuenta);
    }

    @Override
    public void eliminarCuenta(Long numeroCuenta) {
        cuentaRepository.deleteByNumeroCuenta(numeroCuenta);
    }

    @Override
    public List<CuentaDto> obtenerCuentas() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        List<CuentaDto> cuentaDtoList = cuentas.stream()
                .map(cuenta -> Utils.cuentaToDTO(cuenta))
                .collect(Collectors.toList());
        return cuentaDtoList;
    }
}
