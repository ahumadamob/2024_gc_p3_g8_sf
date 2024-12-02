package gr8.imb3.progra3.service;

import java.util.List;

import gr8.imb3.progra3.entity.Proveedor;


public interface IProveedorService {
	public List<Proveedor> buscar();
	public Proveedor buscarPorId(Integer id);
	public Proveedor guardar(Proveedor proveedor);
	public void eliminar(Integer id);
	public Boolean exists(Integer id);
	public Proveedor habilitarProveedor(Proveedor proveedor);
	public Proveedor deshabilitarProveedor(Proveedor proveedor);
	public List<Proveedor> mostrarHabilitados();
	public List<Proveedor> mostrarDeshabilitados();
	public int countDeshabilitados();
}
