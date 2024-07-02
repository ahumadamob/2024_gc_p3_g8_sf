package gr8.imb3.progra3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gr8.imb3.progra3.entity.Empleado;


public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	public Empleado getByDni (Integer dni);
	List<Empleado> findBySupervisorId(Integer supervisorId);
}
