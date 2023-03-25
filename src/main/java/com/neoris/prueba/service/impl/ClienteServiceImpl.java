package com.neoris.prueba.service.impl;

import com.neoris.prueba.dto.ClienteDto;
import com.neoris.prueba.entities.Cliente;
import com.neoris.prueba.entities.Cuenta;
import com.neoris.prueba.repository.ClienteRepository;
import com.neoris.prueba.service.ClienteService;
import com.neoris.prueba.utils.Utils;
import jakarta.ws.rs.NotFoundException;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public ClienteDto crearCliente(ClienteDto clienteDTO) {
        Cliente cliente = Cliente.builder()
                .nombre(clienteDTO.getNombre())
                .genero(clienteDTO.getGenero())
                .edad(clienteDTO.getEdad())
                .identificacion(clienteDTO.getIdentificacion())
                .direccion(clienteDTO.getDireccion())
                .telefono(clienteDTO.getTelefono())
                .contrasena(BCrypt.hashpw(clienteDTO.getContrasena(), BCrypt.gensalt()))
                .estado(clienteDTO.getEstado())
                .build();

        clienteRepository.save(cliente);
        return Utils.clienteToDTO(cliente);
    }

    @Override
    public ClienteDto obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado con id " + id));
        return Utils.clienteToDTO(cliente);
    }

    @Override
    public List<ClienteDto> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteDto> clienteDtoList = clientes.stream()
                .map(cliente -> Utils.clienteToDTO(cliente))
                .collect(Collectors.toList());
        return clienteDtoList;
    }

    @Override
    public ClienteDto actualizarCliente(Long id, ClienteDto clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado con id " + id));

        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        cliente.setContrasena(BCrypt.hashpw(clienteDTO.getContrasena(), BCrypt.gensalt()));

        Cliente clienteActualizado = clienteRepository.save(cliente);
        return Utils.clienteToDTO(clienteActualizado);
    }

    @Override
    public void eliminarCliente(Long id) {
        Cliente Cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado con id " + id));
        clienteRepository.delete(Cliente);
    }

    @Override
    public List<Cuenta> obtenerCuentasPorClienteId(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente no encontrado con id " + clienteId));
        return cliente.getCuentaList();
    }
}
