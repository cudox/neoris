package com.neoris.prueba.service.impl;

import com.neoris.prueba.dto.ClienteDto;
import com.neoris.prueba.entities.Cliente;
import com.neoris.prueba.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Test
    void crearCliente() {
    }

    @Test
    void obtenerClientePorId() {
    }

    @Test
    void obtenerTodosLosClientes() {

        Cliente cliente1 = Cliente.builder()
                .clienteId(123L)
                .edad(30)
                .nombre("Juan Peréz")
                .estado("OK")
                .genero("M")
                .identificacion("12234")
                .telefono("3130434")
                .direccion("Cr 20 # 2 - 23")
                .build();
        Cliente cliente2 = Cliente.builder()
                .clienteId(124L)
                .edad(49)
                .nombre("Monica Naranjo")
                .estado("OK")
                .genero("F")
                .identificacion("123445")
                .telefono("47823478")
                .direccion("Cr 49 # 2 - 23")
                .build();
        when(clienteRepository.findAll()).thenReturn(List.of(cliente1, cliente2));

        // Ejecución del método a probar
        List<ClienteDto> clienteDtoList = clienteService.obtenerTodosLosClientes();

        // Comprobación del resultado
        assertThat(clienteDtoList).isNotNull();
        assertThat(clienteDtoList).hasSize(2);
        assertThat(clienteDtoList.get(0).getIdentificacion()).isEqualTo("12234");
        assertThat(clienteDtoList.get(1).getIdentificacion()).isEqualTo("123445");
    }

    @Test
    void actualizarCliente() {
    }

    @Test
    void eliminarCliente() {
    }

    @Test
    void obtenerCuentasPorClienteId() {
    }
}