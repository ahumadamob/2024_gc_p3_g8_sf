package gr8.imb3.progra3.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gr8.imb3.progra3.entity.Producto;


public interface ProductoRepository extends JpaRepository<Producto,Integer>{
	
	public List<Producto> getByDisponibilidad(boolean disponibilidad);
}
