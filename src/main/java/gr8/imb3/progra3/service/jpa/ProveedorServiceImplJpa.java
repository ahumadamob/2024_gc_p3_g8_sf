package gr8.imb3.progra3.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import gr8.imb3.progra3.entity.Proveedor;
import gr8.imb3.progra3.repository.ProveedorRepository;
import gr8.imb3.progra3.service.IProveedorService;

@Service
@Primary
public class ProveedorServiceImplJpa implements IProveedorService {
	@Autowired
	ProveedorRepository repo;
	@Override
	public List<Proveedor> buscar() {
		
		return repo.findAll();
	}
	@Override
    public Proveedor buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }
	@Override
	public Proveedor guardar(Proveedor proveedor) {
		return repo.save(proveedor);
	}
	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Boolean exists(Integer id) {
		if (id == null) {
			return false;
		}else {
			return repo.existsById(id);
		}
	}
	@Override
	public Proveedor habilitarProveedor(Proveedor proveedor) {
		proveedor.setHabilitado(true);
		repo.save(proveedor);
		return proveedor;
	}
	@Override
	public Proveedor deshabilitarProveedor(Proveedor proveedor) {
		proveedor.setHabilitado(false);
		repo.save(proveedor);
		return proveedor;
	}
	@Override
	public List<Proveedor> mostrarHabilitados() {
		return repo.getByHabilitado(true);
	}
	@Override
	public List<Proveedor> mostrarDeshabilitados() {
		return repo.getByHabilitado(false);
	}
}
