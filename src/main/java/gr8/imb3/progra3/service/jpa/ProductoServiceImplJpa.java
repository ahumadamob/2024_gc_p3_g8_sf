package gr8.imb3.progra3.service.jpa;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import gr8.imb3.progra3.entity.Producto;
import gr8.imb3.progra3.repository.ProductoRepository;
import gr8.imb3.progra3.service.IProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
@Primary
public class ProductoServiceImplJpa implements IProductoService{

	//Se hace inyeccion de dependencia para ustilizar el archivo de ProductoRepository
	@Autowired
	ProductoRepository repo;
	
	//El metodo devuelve un listado de la entidad Productosutiliza 
	@Override
	public List<Producto> buscarProductos(){
		//utiliza el metodo findall para traer todos los registros de la tabla
		return repo.findAll();
	}
	
	//Este metodo devuelve un objeto Producto, tambien recibe como parametro un id
	@Override
	public Producto buscarProductoPorId (Integer id){
		//Utiliza el metodo findById, al cual le envia el id recibido anteriormente, esto trae el registro que tenga ese id
		//Tambien se utiliza .orElse por si el metodo findbyid no trae ningun regustro, en ese caso, devuelve null
		return repo.findById(id).orElse(null);		
	}
	
	//Este metodo Devuelve un objeto Producto, y recibe como parametro un objeto producto
	@Override
	public Producto guardarProducto(Producto producto){
		//Con la funcion .save, y envandole el objeto obtenido, se guarda ese objeto en la tabla
		repo.save(producto);
		//Finalmente devuelve el objeto para mostrarlo en la consola
		return repo.findById(producto.getId()).orElse(null);
	}
	
	//Este metodo devuelve vacio, y recibe como parametro un id
	@Override
	public void eliminarProducto(Integer id) {
		//Con la funcion deletebyid, enviandole el id recibido, se elimina el registro que tenga el mismo id
		repo.deleteById(id);
	}
	
	//Este metodo devuelve un dato booleano, recibe como parametro un id
	@Override
	public boolean existe(Integer id) {
		//Con la funcion existsById, enviandole el id recibido, podemos saber si existe algun registro con ese id
		//Devuelve false si no existe el registro, y de lo contrario devuelve true
		return repo.existsById(id);
	}
}
