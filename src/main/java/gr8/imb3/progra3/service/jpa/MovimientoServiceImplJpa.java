package gr8.imb3.progra3.service.jpa;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import gr8.imb3.progra3.entity.Movimiento;
import gr8.imb3.progra3.repository.MovimientoRepository;
import gr8.imb3.progra3.service.IMovimientoService;


@Service
@Primary
public class MovimientoServiceImplJpa implements IMovimientoService {

	@Autowired
	MovimientoRepository repo;
	
	@Override
	public List<Movimiento> buscar() {
		return repo.findAll();
	}

	@Override
	public Movimiento buscarPorId(Integer id) {
		return repo.findById(id).orElse(null);	
	}

	@Override
	public Movimiento guardar(Movimiento movimiento) {
		return repo.save(movimiento);
	}

	@Override
	public void eliminar(Integer idMovimiento) {
		repo.deleteById(idMovimiento);
	}

	@Override 
	public boolean existe(Integer id) {
		return repo.existsById(id); 
	}

	@Override
	public List<Movimiento> mostrarMovimientosDeProducto(Integer idProducto) {
		// TODO Auto-generated method stub
		return repo.findByProductoId(idProducto);
	}
}
