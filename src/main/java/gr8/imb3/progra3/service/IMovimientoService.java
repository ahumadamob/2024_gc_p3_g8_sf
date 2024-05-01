package gr8.imb3.progra3.service;

import java.util.List;

import gr8.imb3.progra3.entity.Movimiento;


public interface IMovimientoService {
	public List<Movimiento> buscar();
	public Movimiento buscarPorId(Integer id);
	public Movimiento guardar(Movimiento movimiento);
	public void eliminar(Integer idMovimiento);
	public boolean existe(Integer id);
}
