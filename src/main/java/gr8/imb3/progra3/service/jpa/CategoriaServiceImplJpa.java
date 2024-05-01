package gr8.imb3.progra3.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr8.imb3.progra3.entity.Categoria;
import gr8.imb3.progra3.repository.CategoriaRepository;
import gr8.imb3.progra3.service.ICategoriaService;

@Service
public class CategoriaServiceImplJpa implements ICategoriaService{
	
	@Autowired
	CategoriaRepository repo;
	
	@Override
	public Categoria guardar(Categoria categoria) {
		return repo.save(categoria);
	}
	
	@Override
	public List<Categoria> buscarTodos(){
		return repo.findAll();
	}
	
	@Override
    public Categoria buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
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
	public List<Categoria> buscarHabilitados(boolean habilitado){
		return repo.findByHabilitado(habilitado);
	}

}
