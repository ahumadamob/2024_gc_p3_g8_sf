package gr8.imb3.progra3.service;

import java.util.List;

import gr8.imb3.progra3.entity.Categoria;

public interface ICategoriaService {

	public List<Categoria> buscarTodos();

	public List<Categoria> buscarHabilitados(boolean habilitado);

	public Categoria buscarPorId(Integer id);

	public Categoria guardar(Categoria categoria);

	public void eliminar(Integer id);

	public boolean existe(Integer id);

}
