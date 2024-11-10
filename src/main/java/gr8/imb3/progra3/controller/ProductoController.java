package gr8.imb3.progra3.controller;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr8.imb3.progra3.entity.Producto;
import gr8.imb3.progra3.service.IProductoService;
import jakarta.validation.ConstraintViolationException;

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
		
		@PutMapping("/{id}/actualizarstock")	
		public ResponseEntity<APIResponse<Producto>> actualizarStock(@RequestBody Producto producto, @PathVariable("id") Integer id) {
			if(productoService.existe(id)) {
				if(producto.getCantidad() < 0) {
				  return ResponseUtil.badRequest("No se aceptan numeros negativos.");
				}
				else {
					Producto p = productoService.buscarProductoPorId(id);
					p.setCantidad(producto.getCantidad());
				
					return ResponseUtil.success(productoService.guardarProducto(p));
				}
				
				
			}else {
				return ResponseUtil.badRequest("No existe producto con id: " + id + ".");
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
	
	@GetMapping("/Disponibles")
	public ResponseEntity<APIResponse<List<Producto>>> mostrarDisponibles() {
		List<Producto> productos = productoService.mostrarDisponibles();
		if (!productos.isEmpty()) {
			return ResponseUtil.success(productos);
		}else {
			return ResponseUtil.notFound("No se encontraron disponibles.");
		}
		
	}
	@GetMapping("/NoDisponibles")
	public ResponseEntity<APIResponse<List<Producto>>> mostrarNoDisponibles() {
		List<Producto> productos = productoService.mostrarNoDisponibles();
		if (!productos.isEmpty()) {
			return ResponseUtil.success(productos);
		}else {
			return ResponseUtil.notFound("No se encontraron no disponibles.");
		}
		
	}
		
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex){
        return ResponseUtil.handleConstraintException(ex);
    }
    @ExceptionHandler(Exception.class)
   public ResponseEntity<APIResponse<Object>> handleException(Exception ex) {
       return ResponseUtil.badRequest(ex.getMessage());
   }
    @PutMapping("/{id}/cambiar-precio")
    public ResponseEntity<APIResponse<Producto>> cambiarPrecio(
            @PathVariable Integer id,
            @RequestBody Map<String, Double> requestBody) throws JpaSystemException {
        Double nuevoPrecio = requestBody.get("nuevoPrecio");

        if (nuevoPrecio == null) {
            return ResponseUtil.badRequest("El campo 'nuevoPrecio' es requerido.");
        }

        try {
            Producto productoActualizado = productoService.actualizarPrecio(id, nuevoPrecio);
            return ResponseUtil.success(productoActualizado);

        } catch (NoSuchElementException e) {
            return ResponseUtil.notFound("Producto no encontrado.");
        } catch (IllegalArgumentException e) {
            return ResponseUtil.badRequest(e.getMessage());
        } catch (DataAccessException e) {
            // Manejar problemas de JPA como restricciones de base de datos
            return ResponseUtil.internalServerError("Error de base de datos: " + ((Throwable) e).getMessage());
        } catch (Exception e) {
            return ResponseUtil.internalServerError("Error interno al procesar la solicitud.");
        }
    }
}
