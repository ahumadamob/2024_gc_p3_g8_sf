package gr8.imb3.progra3.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr8.imb3.progra3.entity.Movimiento;
import gr8.imb3.progra3.service.IMovimientoService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

//localhost:8080/api/imb3/movimientos

@RestController
@RequestMapping("/api/imb3/movimientos")
public class MovimientoController {
	
	@Autowired
	IMovimientoService service;
	
	@GetMapping
	public ResponseEntity<APIResponse<List<Movimiento>>> mostrarTodos(){
		  List<Movimiento> movimientos = service.buscar();
	         if(!movimientos.isEmpty()) { 
	             return ResponseUtil.success(movimientos); 
	         }
	         else { 
	             return ResponseUtil.notFound("No se encontraron movimientos."); 
	         }
	    }
	
	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Movimiento>> mostrarMovimientoPorId(@PathVariable("id") Integer id){
		if(service.existe(id)) {
			return ResponseUtil.success(service.buscarPorId(id));	
		}else {
			return ResponseUtil.notFound("No se encontro el Movimiento con el id: "+id+".");
		}
	}
	
	@GetMapping("/{idProducto}/movimientos")
	public ResponseEntity<APIResponse<List<Movimiento>>> mostrarMovimientosDeProducto(@PathVariable("idProducto") Integer idProducto){
		if(service.existe(idProducto)) {
			return ResponseUtil.success(service.mostrarMovimientosDeProducto(idProducto));	
		}else {
			return ResponseUtil.notFound("Este producto no tiene movimientos asociados.");
		}
	}
	
	@PostMapping
	public ResponseEntity<APIResponse<Movimiento>> crearMovimiento(@RequestBody Movimiento movimiento) {
		if(service.existe(movimiento.getId())) {
			  return ResponseUtil.badRequest("Ya existe el movimiento con el id: "+ movimiento.getId() +".");
		}else {		
			return ResponseUtil.created(service.guardar(movimiento));
		}			
	}
	
	@PutMapping	
	public ResponseEntity<APIResponse<Movimiento>> modificarMovimiento(@RequestBody Movimiento movimiento) {
		if(service.existe(movimiento.getId())) {
			
			  return ResponseUtil.success(service.guardar(movimiento));
		}else {
			 return ResponseUtil.badRequest("No existe el movimiento con el id: "+movimiento.getId()+".");
		}
	}
	
	// @DeleteMapping es el verbo para eliminar un objeto el cual recibe un id por url
	
	@DeleteMapping("/{id}")	
	// el metodo devuelve un ResponseEntity de la entidad movimiento,y recibe como parametro el id de la url 
	
	public ResponseEntity<APIResponse<Movimiento>> eliminarMovimiento(@PathVariable("id") Integer id) {
		// verifica en el metodo existe. si existe un registro con esa id...
		
		if(service.existe(id)) {
			//Crea una variable temporal con el objeto a elominar
			
			 Movimiento movimientoEliminado = service.buscarPorId(id);
			 //elimina con el objeto con el id recibido
			 
	            service.eliminar(id);
	            //retorna un respuesta de tupo success devolviendo el objeto eliminado
	            
	            return ResponseUtil.success(movimientoEliminado);
		}
		// si no existe un registro con esa id...
		else {
			// se muestra el mensaje "No existe un movimiento con el id =" y muestra el id del movimiento buscado
	          return ResponseUtil.badRequest("No existe el movimiento con el id: "+id+".");		
		}
	}
	
	@PutMapping("/{id}/anular")
	public ResponseEntity<APIResponse<Movimiento>> anularMovimiento(@PathVariable("id") Integer id) {
		if(service.existe(id)) {
			Movimiento movimiento = service.buscarPorId(id);
			if(movimiento.isAnulado()) {
				return ResponseUtil.badRequest("El movimiento ya se encuentra anulado.");
			}
			else {
				movimiento.setAnulado(true);
				return ResponseUtil.success(service.guardar(movimiento));
			}
		}else {
			 return ResponseUtil.badRequest("No existe el movimiento con el id: " + id + ".");
		}
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<?>> handleException(ConstraintViolationException ex){
		List<String> errors = new ArrayList<>();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.add(violation.getMessage());
		}
		APIResponse<Movimiento> response = new APIResponse<Movimiento>(HttpStatus.BAD_REQUEST.value(), errors, null);
		return ResponseEntity.badRequest().body(response);
	}

}
