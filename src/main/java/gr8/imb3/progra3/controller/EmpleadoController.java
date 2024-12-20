package gr8.imb3.progra3.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import gr8.imb3.progra3.entity.Empleado;
import gr8.imb3.progra3.service.IEmpleadoService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/Empleado")
public class EmpleadoController {

    // Inyección del servicio IEmpleadoService
    @Autowired
    IEmpleadoService service;

    // Endpoint POST para validar y guardar empleado
    @PostMapping("/validar")
    public ResponseEntity<APIResponse<Empleado>> validarYGuardarEmpleado(@RequestBody @Valid Empleado empleado) {
        try {
            // Llamamos al servicio para validar y guardar el empleado
            Empleado empleadoGuardado = service.validarYGuardarEmpleado(empleado); // Usamos 'service' en lugar de 'empleadoService'
            return ResponseUtil.created(empleadoGuardado);
        } catch (Exception e) {
            // Si ocurre algún error, lo capturamos y lo devolvemos en el formato adecuado
            return ResponseUtil.badRequest(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<Empleado>>> mostrarTodos() {
        List<Empleado> empleados = service.buscar();
        if (!empleados.isEmpty()) {
            return ResponseUtil.success(empleados);
        } else {
            return ResponseUtil.notFound("No se encontraron empleados.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Empleado>> mostrarEmpleadoPorId(@PathVariable Integer id) {
        if (service.existe(id)) {
            return ResponseUtil.success(service.buscarPorId(id));
        } else {
            return ResponseUtil.notFound("No se encontró el empleado con el id: " + id + ".");
        }
    }

    @PostMapping
    public ResponseEntity<APIResponse<Empleado>> crearEmpleado(@RequestBody Empleado empleado) {
        if (service.existe(empleado.getId())) {
            return ResponseUtil.badRequest(
                "Ya existe el empleado con el id: " + empleado.getId() + ". O con el dni: " + empleado.getDni());
        } else {
            Empleado empleadoCompararPorDni = service.buscarPorDni(empleado.getDni());
            if (empleadoCompararPorDni == null) {
                return ResponseUtil.created(service.guardar(empleado));
            } else {
                return ResponseUtil.badRequest("Ya existe el empleado con el id: " + empleado.getId()
                    + ". O con el dni: " + empleado.getDni());
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<Empleado>> modificarEmpleado(@RequestBody Empleado empleado) {
        if (service.existe(empleado.getId())) {
            Empleado empleadoCompararPorDni = service.buscarPorDni(empleado.getDni());
            if ((empleadoCompararPorDni == null) || (empleadoCompararPorDni.getDni() == empleado.getDni())) {
                return ResponseUtil.success(service.guardar(empleado));
            } else {
                return ResponseUtil.badRequest(
                    "Ya existe el empleado con el dni: " + empleado.getDni());
            }
        } else {
            return ResponseUtil.badRequest(
                "No existe el empleado con el id: " + empleado.getId() + ". O con el dni: " + empleado.getDni());
        }
    }

    @PutMapping("/{id}/cambiar-rol")
    public ResponseEntity<APIResponse<Empleado>> cambiarTipoPuesto(@PathVariable Integer id, @RequestBody Map<String, String> requestBody) {
        String tipoPuesto = requestBody.get("tipoPuesto");

        if (service.existe(id)) {
            if ("empleado".equals(tipoPuesto) || "supervisor".equals(tipoPuesto)) {
                Empleado empleado = service.buscarPorId(id);
                empleado.setTipoPuesto(tipoPuesto);
                return ResponseUtil.success(service.guardar(empleado));
            } else {
                return ResponseUtil.badRequest(
                    "El tipo de puesto / rol '" + tipoPuesto + "' no es válido.");
            }
        } else {
            return ResponseUtil.badRequest(
                "No existe el empleado con el id: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Empleado>> eliminarEmpleado(@PathVariable("id") Integer id) {
        if (service.existe(id)) {
            Empleado empleadoEliminado = service.buscarPorId(id);
            service.eliminar(id);
            return ResponseUtil.success(empleadoEliminado);
        } else {
            return ResponseUtil.badRequest("No existe el empleado con el id: " + id + ".");
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<APIResponse<Empleado>> mostrarEmpleadoPorDni(@PathVariable Integer dni) {
        if (service.buscarPorDni(dni) != null) {
            return ResponseUtil.success(service.buscarPorDni(dni));
        } else {
            return ResponseUtil.notFound("No se encontró el empleado con el dni: " + dni + ".");
        }
    }

    @GetMapping("/activar_desactivar/{id}")
    public ResponseEntity<APIResponse<Empleado>> activar_desactivar(@PathVariable Integer id) {
        if (service.existe(id)) {
            return ResponseUtil.success(service.activar_desactivar(id));
        } else {
            return ResponseUtil.notFound("No se encontró el empleado con el id: " + id + ".");
        }
    }

    @GetMapping("/{empleadoId}/supervisados")
    public ResponseEntity<APIResponse<Map<String, Object>>> obtenerEmpleadoYSupervisados(
            @PathVariable Integer empleadoId) {
        if (!service.existe(empleadoId)) {
            return ResponseUtil.notFound("No se encontró el empleado con el id: " + empleadoId + ".");
        }
        Empleado empleado = service.buscarPorId(empleadoId);
        List<Empleado> supervisados = service.buscarSupervisadosPorId(empleadoId);
        Map<String, Object> response = new HashMap<>();
        response.put("empleado", empleado);
        response.put("supervisados", supervisados);
        return ResponseUtil.success(response);
    }

    // Manejo de excepciones
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseUtil.handleConstraintException(ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Object>> handleException(Exception ex) {
        return ResponseUtil.badRequest(ex.getMessage());
    }
}
