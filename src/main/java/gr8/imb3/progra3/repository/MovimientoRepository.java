package gr8.imb3.progra3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gr8.imb3.progra3.entity.Movimiento;


public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {
	List<Movimiento> findByProductoId(Integer idProducto);
}
