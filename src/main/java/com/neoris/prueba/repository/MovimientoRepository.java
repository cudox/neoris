package com.neoris.prueba.repository;

import com.neoris.prueba.entities.Cuenta;
import com.neoris.prueba.entities.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByCuenta(Cuenta cuenta);
    List<Movimiento> findByCuentaAndFechaBetween(Cuenta cuenta, LocalDate fechaInicio, LocalDate fechaFin);
}
