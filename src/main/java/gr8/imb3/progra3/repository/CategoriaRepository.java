package gr8.imb3.progra3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gr8.imb3.progra3.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	
	public List<Categoria> findByHabilitado(boolean habilitados);


}
