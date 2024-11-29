package gr8.imb3.progra3.service;

import java.util.List;

import gr8.imb3.progra3.entity.Empleado;
import jakarta.validation.Valid;

public interface IEmpleadoService {
	
	public List<Empleado> buscar();
	public Empleado buscarPorId(Integer id);
	public Empleado guardar(Empleado empleado);
	public void eliminar(Integer id);
	public boolean existe (Integer id);
	public Empleado buscarPorDni(Integer dni);
	public Empleado activar_desactivar(Integer id);
	public List<Empleado> buscarSupervisadosPorId(Integer id);
	public Empleado validarYGuardarEmpleado(Empleado empleado) throws Exception;
	
}
	

