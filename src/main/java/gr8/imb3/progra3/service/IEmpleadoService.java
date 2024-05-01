package gr8.imb3.progra3.service;

import java.util.List;

import gr8.imb3.progra3.entity.Empleado;

public interface IEmpleadoService {
	
	public List<Empleado> buscar();
	public Empleado buscarPorId(Integer id);
	public Empleado guardar(Empleado empleado);
	public void eliminar(Integer id);
	public boolean existe (Integer id);
	 public Empleado buscarPorDni(Integer dni);
	
}
	

