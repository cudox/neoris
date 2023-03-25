package com.neoris.prueba.service;

import com.neoris.prueba.dto.ClienteDto;
import com.neoris.prueba.entities.Cuenta;

import java.util.List;

public interface ClienteService {
    ClienteDto crearCliente(ClienteDto clienteDTO);
    ClienteDto obtenerClientePorId(Long id);
    List<ClienteDto> obtenerTodosLosClientes();
    ClienteDto actualizarCliente(Long id, ClienteDto clienteDTO);
    void eliminarCliente(Long id);

    List<Cuenta> obtenerCuentasPorClienteId(Long clienteId);
}
