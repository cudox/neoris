package com.neoris.prueba.repository;

import com.neoris.prueba.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aquí podrías definir otros métodos para buscar, filtrar o paginar entidades Cliente
}
