package gr8.imb3.progra3.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import gr8.imb3.progra3.entity.Empleado;
import gr8.imb3.progra3.repository.EmpleadoRepository;
import gr8.imb3.progra3.service.IEmpleadoService;

@Service
@Primary
public class EmpleadoServiceImplJpa implements IEmpleadoService {

	@Autowired
	EmpleadoRepository repo;

	@Override
	public List<Empleado> buscar() {
		return repo.findAll();

	}

	@Override
	public Empleado buscarPorId(Integer id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Empleado guardar(Empleado empleado) {
		return repo.save(empleado);

	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);

	}

	@Override
	public boolean existe(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public Empleado buscarPorDni(Integer dni) {
		return repo.getByDni(dni);
	}

	@Override
	public Empleado activar_desactivar(Integer id) {
		Empleado empleado = new Empleado();
		empleado = repo.findById(id).orElse(null);
		if (empleado != null) {
	        empleado.setActivo(!empleado.isActivo()); 
	        repo.save(empleado); 
	    }
		return empleado;
	}

	@Override
	public List<Empleado> buscarSupervisadosPorId(Integer id) {
		 return repo.findBySupervisorId(id);
	}
}
