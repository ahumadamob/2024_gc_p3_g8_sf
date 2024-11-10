package gr8.imb3.progra3.service;

import java.util.List;

import gr8.imb3.progra3.entity.Producto;


public interface IProductoService {
	public List<Producto> buscarProductos();
	public Producto buscarProductoPorId (Integer id);
	public Producto guardarProducto(Producto producto);
	public void eliminarProducto(Integer id);
	public boolean existe(Integer id);
	public List<Producto> mostrarDisponibles();
	public List<Producto> mostrarNoDisponibles();
	public Producto actualizarPrecio(Integer id, Double nuevoPrecio);
	
}
