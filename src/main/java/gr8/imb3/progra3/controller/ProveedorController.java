package gr8.imb3.progra3.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.misc.Array2DHashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr8.imb3.progra3.entity.Categoria;
import gr8.imb3.progra3.entity.Proveedor;
import gr8.imb3.progra3.service.ICategoriaService;
import gr8.imb3.progra3.service.IProveedorService;
import jakarta.validation.ConstraintViolationException;


@RestController 
@RequestMapping("/api/v1/proveedores")
public class ProveedorController {

	@Autowired
	IProveedorService proveedorService;

	@Autowired
	ICategoriaService categoriaService;

 	@GetMapping
 	public ResponseEntity<APIResponse<List<Proveedor>>> mostrarTodos() {
 		List<Proveedor> proveedores = proveedorService.buscar();
 		if(!proveedores.isEmpty()) {
 			return ResponseUtil.success(proveedores);
 		}
 		else {
 			return ResponseUtil.notFound("No se encontraron proveedores.");
 		}
	}
 	@GetMapping("/{id}")
	public ResponseEntity<APIResponse<Proveedor>> mostrarProveedorPorId(@PathVariable Integer id) {
		if(proveedorService.exists(id)) {
			return ResponseUtil.success(proveedorService.buscarPorId(id).getCategoria().toString());
		}else {
			return ResponseUtil.notFound("No se encontro el proveedor con el id: "+id+".");	
		}
	}
 	@PostMapping
    public ResponseEntity<APIResponse<Proveedor>> crearProveedor(@RequestBody Proveedor proveedor) {
        if(proveedorService.exists((proveedor.getId()))) {
            return ResponseUtil.badRequest("Ya existe el proveedor con el id: "+proveedor.getId()+".");
        }else {
            return ResponseUtil.created(proveedorService.guardar(proveedor));
        }
    }
 	@PostMapping("/nuevo")
    public ResponseEntity<APIResponse<Proveedor>> crearProveedorNuevo(@RequestBody Proveedor proveedor) {
        if(proveedorService.exists((proveedor.getId()))) {
            return ResponseUtil.badRequest("Ya existe el proveedor con el id: "+proveedor.getId()+".");
        }else {
        	if(proveedor.getCategoria().size() > 3) {
        		return ResponseUtil.badRequest("El proveedor no puede tener mas de 3 categorias.");
        	}
        	if(proveedorService.countDeshabilitados() > 10 && proveedor.getHabilitado() == false) {
        		return ResponseUtil.badRequest("Ya hay 10 o mas proveedores dehabilitados.");
        	}
        	else {
        		return ResponseUtil.created(proveedorService.guardar(proveedor));
        	}
        }
    }
 	
 	@PutMapping	
	public ResponseEntity<APIResponse<Proveedor>> modificarProveedor(@RequestBody Proveedor proveedor) {
		if(proveedorService.exists(proveedor.getId())) {
			return ResponseUtil.success(proveedorService.guardar(proveedor));
		}
		else {
			return ResponseUtil.badRequest("No existe el proveedor con el id: "+proveedor.getId()+".");
		}
	}
 	@PutMapping("/{proveedorId}/categorias")
	public ResponseEntity<APIResponse<Proveedor>> asociarCategoriaProveedor(@RequestBody List<Integer> idCategorias, @PathVariable("proveedorId") Integer idProveedor) {
		if(proveedorService.exists(idProveedor)) {
			Set<Categoria> categoriasValidas = new Array2DHashSet<>();
			List<Integer> categoriasInvalidas = new ArrayList<>();
			for (Integer id : idCategorias) {
				if (categoriaService.existe(id)) {
					categoriasValidas.add(categoriaService.buscarPorId(id));
				}
				else {
					categoriasInvalidas.add(id);
				}
			}
			if (!categoriasValidas.isEmpty()){
				Proveedor proveedorAfectado = proveedorService.buscarPorId(idProveedor);
				proveedorAfectado.setCategoria(categoriasValidas);
				return ResponseUtil.success(proveedorService.guardar(proveedorAfectado));
			}
			else {
				return ResponseUtil.badRequest("No existen las categorias con los id: "+categoriasInvalidas+".");
			}
		}
		else {
			return ResponseUtil.badRequest("No existe el proveedor con el id: "+idProveedor+".");
		}
	}
 	@DeleteMapping("/{id}")	
	public ResponseEntity<APIResponse<Proveedor>> eliminarProveedor(@PathVariable("id") Integer id) {
		if(proveedorService.exists(id)) {
			Proveedor proveedorEliminado = proveedorService.buscarPorId(id);
			proveedorService.eliminar(id);
			return ResponseUtil.success(proveedorEliminado);
			
		}else {
			return ResponseUtil.badRequest("No existe el proveedor con el id: "+id+".");		
		}
	}
 	@PostMapping("/habilitar/{id}")
	public ResponseEntity<APIResponse<Proveedor>> habilitarProducto(@PathVariable("id") Integer id) {
		if(proveedorService.exists(id)) {
			return ResponseUtil.success(proveedorService.habilitarProveedor(proveedorService.buscarPorId(id)));
		}else {
			return ResponseUtil.badRequest("No existe proveedor con id: " + id + ".");
		}
	}
	@PostMapping("/deshabilitar/{id}")
	public ResponseEntity<APIResponse<Proveedor>> deshabilitarProducto(@PathVariable("id") Integer id) {
		if(proveedorService.exists(id)) {
			return ResponseUtil.success(proveedorService.deshabilitarProveedor(proveedorService.buscarPorId(id)));
		}else {
			return ResponseUtil.badRequest("No existe proveedor con id: " + id + ".");
		}
	}
	@GetMapping("/habilitados")
	public ResponseEntity<APIResponse<List<Proveedor>>> mostrarHabilitados() {
		List<Proveedor> proveedores = proveedorService.mostrarHabilitados();
		if (!proveedores.isEmpty()) {
			return ResponseUtil.success(proveedores);
		}else {
			return ResponseUtil.notFound("No se encontraron proveedores.");
		}
	}
	@GetMapping("/deshabilitados")
	public ResponseEntity<APIResponse<List<Proveedor>>> mostrarDeshabilitados() {
		List<Proveedor> proveedores = proveedorService.mostrarDeshabilitados();
		if (!proveedores.isEmpty()) {
			return ResponseUtil.success(proveedores);
		}else {
			return ResponseUtil.notFound("No se encontraron proveedores.");
		}
	}
	@DeleteMapping("/eliminarDeshabilitado/{id}")	
	public ResponseEntity<APIResponse<Proveedor>> eliminarProveedorDeshabilitado(@PathVariable("id") Integer id) {
		if(proveedorService.exists(id)) {
			Proveedor proveedorEliminado = proveedorService.buscarPorId(id);
			if(proveedorEliminado.getHabilitado()) {
				return ResponseUtil.badRequest("El proveedor con el id: "+id+" esta habilitado");
			}else {
				proveedorService.eliminar(id);
				return ResponseUtil.success(proveedorEliminado);
			}
		}else {
			return ResponseUtil.badRequest("No existe el proveedor con el id: "+id+".");		
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
 }