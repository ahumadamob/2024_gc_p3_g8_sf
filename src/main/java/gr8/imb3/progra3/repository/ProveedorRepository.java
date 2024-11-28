package gr8.imb3.progra3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gr8.imb3.progra3.entity.Proveedor;


public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
	public List<Proveedor> getByHabilitado(boolean habilitado);
	public int countByHabilitadoFalse();
}
