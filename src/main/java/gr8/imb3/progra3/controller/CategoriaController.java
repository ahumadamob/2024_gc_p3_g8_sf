package gr8.imb3.progra3.controller;

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

import gr8.imb3.progra3.entity.Categoria;
import gr8.imb3.progra3.service.ICategoriaService;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

	@Autowired
	ICategoriaService servicio;

	// post
	@PostMapping
	public ResponseEntity<APIResponse<Categoria>> guardarCategoria(@RequestBody Categoria categoria) {
		if (servicio.existe(categoria.getId())) {
			return ResponseUtil.error(HttpStatus.CONFLICT,"Ya existe una categoría con el mismo nombre");
		} else if (!servicio.isHabilitado(categoria.getHabilitado()))
			return ResponseUtil.badRequest("La categoría debe estar habilitada para ser creada");
		else {
			return ResponseUtil.created(servicio.guardar(categoria));
		}
	}

	// get
	@GetMapping
	public ResponseEntity<APIResponse<List<Categoria>>> buscarCategoriaTodos() {
		List<Categoria> categorias = servicio.buscarTodos();
		if (categorias.isEmpty()) {
			return ResponseUtil.notFound("No se encontraron categorias");
		} else {
			return ResponseUtil.success(categorias);
		}
	}

	@GetMapping("/habilitadas")
	public ResponseEntity<APIResponse<List<Categoria>>> buscarHabilitadas() {
		List<Categoria> categoriaHabilitadas = servicio.buscarHabilitados(true);
		if (categoriaHabilitadas.isEmpty()) {
			return ResponseUtil.badRequest("No se encuentra categorias habilitadas");
		} else {
			return ResponseUtil.success(categoriaHabilitadas);
		}
	}

	@GetMapping("/deshabilitadas")
	public ResponseEntity<APIResponse<List<Categoria>>> buscarDeshabilitadas() {
		List<Categoria> categoriaHabilitadas = servicio.buscarHabilitados(false);
		if (categoriaHabilitadas.isEmpty()) {
			return ResponseUtil.badRequest("No se encuentra categorias habilitadas");
		} else {
			return ResponseUtil.success(categoriaHabilitadas);
		}
	}

	// getid
	@GetMapping("/{id}") // Utilizaremos la anotacion "getmapping" para hacer darle una funcion a la
							// funcion get de nuestro http
	// Agregaremos "("/{id}")" para buscar en la direccion de la ID que utilizaremos
	// en el postman

	// damos un acceso publico, Su entorno sera con las entidades y utilizando la
	// apiresponse en categoria
	// Utilizaremos @pathvarible para poder utilizar la ID directamente en la URL
	// como un parametro de tipo integer que (en este caso se llama id)
	public ResponseEntity<APIResponse<Categoria>> buscarCategoriaPorId(@PathVariable Integer id) {
		// creamos un objeto llamado categoria y declaramos su valor, que sera igual a
		// los datos de las celdas utilizadas en la tabla de su base de datos
		// vinculadas por su id
		Categoria categoria = servicio.buscarPorId(id);
		// Cromprobamos si la id pertenes a la base de datos
		if (servicio.existe(id)) {// si existe devolvera los valores de las celdas de la base de datos
			return ResponseUtil.success(categoria);
		} else {// Si no existe devolvera el mensaje, "No se encontro la categoria por el ID
			return ResponseUtil.notFound("No se encontro la categoria por el ID");
		}
	}

	// put
	@PutMapping("/{id}")
	public ResponseEntity<APIResponse<Categoria>> modificarCategoria(@PathVariable Integer id,
			@RequestBody Categoria categoria) {
		if (servicio.existe(categoria.getId())) {
			servicio.guardar(categoria);
			return ResponseUtil.success(categoria);
		} else {
			return ResponseUtil.badRequest("No existe la categoria deseada");
		}
	}

	// puthabilitar
	@PutMapping("/habilitar/{id}")
	public ResponseEntity<APIResponse<Categoria>> habilitarCategoria(@PathVariable Integer id) {
		if (servicio.existe(id)) {
			Categoria categoria = servicio.buscarPorId(id);
			categoria.setHabilitado(true);
			servicio.guardar(categoria);
			return ResponseUtil.success(categoria);
		} else {
			return ResponseUtil.badRequest("No existe la categoria deseada");
		}
	}

	// putdeshabilitar
	@PutMapping("/deshabilitar/{id}")
	public ResponseEntity<APIResponse<Categoria>> deshabilitarCategoria(@PathVariable Integer id) {
		if (servicio.existe(id)) {
			Categoria categoria = servicio.buscarPorId(id);
			categoria.setHabilitado(false);
			servicio.guardar(categoria);
			return ResponseUtil.success(categoria);
		} else {
			return ResponseUtil.badRequest("No existe la categoria deseada");
		}
	}

	// delete
	@DeleteMapping("/{id}")
	public ResponseEntity<APIResponse<Categoria>> eliminarCategoria(@PathVariable("id") Integer id) {
		if (servicio.existe(id)) {
			servicio.eliminar(id);
			return ResponseUtil.success("se elimino");
		} else {
			return ResponseUtil.badRequest("No existe la categoria deseada");
		}
	}

	@DeleteMapping("/eliminardeshabilitado/{id}")
	public ResponseEntity<APIResponse<Categoria>> eliminarDeshabilitado(@PathVariable("id") Integer id) {
		if (servicio.existe(id)) {
			Categoria categoria = servicio.buscarPorId(id);
			if (categoria.getHabilitado() == true) {
				return ResponseUtil.badRequest("No se pudo eliminar categoria habilitadas.");
			} else {
				servicio.eliminar(id);
				return ResponseUtil.success("Se eliminio");
			}
		} else {
			return ResponseUtil.badRequest("No existe la categoria deseada");
		}
	}


	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Object>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}

}