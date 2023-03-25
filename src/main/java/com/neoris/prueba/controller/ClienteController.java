package com.neoris.prueba.controller;

import com.neoris.prueba.dto.ClienteDto;
import com.neoris.prueba.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    ClienteService clienteService;
    @GetMapping("/clientes")
    public List<ClienteDto> getClientes() {
        return clienteService.obtenerTodosLosClientes();
    }

    @PostMapping("/clientes")
    public ClienteDto crearCliente(@RequestBody ClienteDto cliente) {
        return clienteService.crearCliente(cliente);
    }

    @GetMapping("/clientes/{id}")
    public ClienteDto getCliente(@PathVariable long id) {
        return clienteService.obtenerClientePorId(id);
    }

    @PutMapping("/clientes/{id}")
    public ClienteDto actualizarCliente(@PathVariable long id, @RequestBody ClienteDto cliente) {
        return clienteService.actualizarCliente(id, cliente);
    }

    @DeleteMapping("/clientes/{id}")
    public void eliminarCliente(@PathVariable long id) {
        clienteService.eliminarCliente(id);
    }
}
