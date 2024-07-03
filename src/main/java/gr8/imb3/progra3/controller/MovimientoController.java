package gr8.imb3.progra3.controller;

import gr8.imb3.progra3.entity.Movimiento;
import gr8.imb3.progra3.service.IMovimientoService;
import gr8.imb3.progra3.service.IProductoService;
import gr8.imb3.progra3.service.IEmpleadoService;
import gr8.imb3.progra3.service.IProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/imb3/movimientos")
public class MovimientoController {

    @Autowired
    private IMovimientoService movimientoService;

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IEmpleadoService empleadoService;

    @Autowired
    private IProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<APIResponse<List<Movimiento>>> mostrarTodos() {
        List<Movimiento> movimientos = movimientoService.buscar();
        if (!movimientos.isEmpty()) {
            return ResponseUtil.success(movimientos);
        } else {
            return ResponseUtil.notFound("No se encontraron movimientos.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<Movimiento>> mostrarMovimientoPorId(@PathVariable("id") Integer id) {
        if (movimientoService.existe(id)) {
            return ResponseUtil.success(movimientoService.buscarPorId(id));
        } else {
            return ResponseUtil.notFound("No se encontró el Movimiento con el id: " + id + ".");
        }
    }

    @PostMapping
    public ResponseEntity<APIResponse<Movimiento>> crearMovimiento(@RequestBody Movimiento movimiento) {
        
        if (!productoService.existe(movimiento.getProducto().getId())) {
            return ResponseUtil.badRequest("No existe el producto con el id: " + movimiento.getProducto().getId() + ".");
        }
        if (!empleadoService.existe(movimiento.getEmpleado().getId())) {
            return ResponseUtil.badRequest("No existe el empleado con el id: " + movimiento.getEmpleado().getId() + ".");
        }
        if (!proveedorService.exists(movimiento.getProveedor().getId())) {
            return ResponseUtil.badRequest("No existe el proveedor con el id: " + movimiento.getProveedor().getId() + ".");
        }

        Movimiento movimientoGuardado = movimientoService.guardar(movimiento);
        if (movimientoGuardado != null) {
            return ResponseUtil.created(movimientoGuardado);
        } else {
            return ResponseUtil.badRequest("Error al intentar guardar el movimiento.");
        }
    }

    @PutMapping
    public ResponseEntity<APIResponse<Movimiento>> modificarMovimiento(@RequestBody Movimiento movimiento) {
      
        if (!productoService.existe(movimiento.getProducto().getId())) {
            return ResponseUtil.badRequest("No existe el producto con el id: " + movimiento.getProducto().getId() + ".");
        }
        if (!empleadoService.existe(movimiento.getEmpleado().getId())) {
            return ResponseUtil.badRequest("No existe el empleado con el id: " + movimiento.getEmpleado().getId() + ".");
        }
        if (!proveedorService.exists(movimiento.getProveedor().getId())) {
            return ResponseUtil.badRequest("No existe el proveedor con el id: " + movimiento.getProveedor().getId() + ".");
        }

    
        Movimiento movimientoModificado = movimientoService.guardar(movimiento);
        if (movimientoModificado != null) {
            return ResponseUtil.success(movimientoModificado);
        } else {
            return ResponseUtil.badRequest("No existe el movimiento con el id: " + movimiento.getId() + ".");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<Movimiento>> eliminarMovimiento(@PathVariable("id") Integer id) {
        if (movimientoService.existe(id)) {
            Movimiento movimientoEliminado = movimientoService.buscarPorId(id);
            movimientoService.eliminar(id);
            return ResponseUtil.success(movimientoEliminado);
        } else {
            return ResponseUtil.badRequest("No existe el movimiento con el id: " + id + ".");
        }
    }

   
    
}