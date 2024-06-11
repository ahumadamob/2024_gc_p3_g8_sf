package gr8.imb3.progra3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr8.imb3.progra3.entity.Producto;
import gr8.imb3.progra3.service.IProductoService;

@RestController
@RequestMapping("/api/imb3/Producto")
public class ProductoController {

	@Autowired
	IProductoService productoService;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Producto>>> mostrarTodos() {
		List<Producto> productos = productoService.buscarProductos();
		if (!productos.isEmpty()) {
			return ResponseUtil.success(productos);
		}else {
			return ResponseUtil.notFound("No se encontraron produtos.");
		}
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Producto>> mostrarProductoPorId(@PathVariable("id") Integer id) {
		if(productoService.existe(id)) {
			return ResponseUtil.success(productoService.buscarProductoPorId(id));
		}else {
			return ResponseUtil.notFound("No se encontro el producto con el id: " + id + ".");			
		}
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Producto>> guardarProducto(@RequestBody Producto producto) {
		if(productoService.existe(producto.getId())) {
			return ResponseUtil.badRequest("Ya existe el producto con el id: " + producto.getId() + ".");
		}else {
			return ResponseUtil.created(productoService.guardarProducto(producto));
		}
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Producto>> modificarProducto(@RequestBody Producto producto) {
		if(productoService.existe(producto.getId())) {
			return ResponseUtil.success(productoService.guardarProducto(producto));
		}else {
			return ResponseUtil.badRequest("No existe producto con id: " + producto.getId() + ".");
		}
	}
	
	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Producto>> eliminarProducto(@PathVariable("id") Integer id) {
		if(productoService.existe(id)) {
			productoService.eliminarProducto(id);
			Producto productoEliminado = productoService.buscarProductoPorId(id);
			return ResponseUtil.success(productoEliminado);
			
		}else {
			return ResponseUtil.badRequest("No existe el producto con el id: " + id + ".");
		}
	}
}
