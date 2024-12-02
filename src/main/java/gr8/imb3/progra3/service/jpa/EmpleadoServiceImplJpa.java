package gr8.imb3.progra3.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import gr8.imb3.progra3.entity.Empleado;
import gr8.imb3.progra3.repository.EmpleadoRepository;
import gr8.imb3.progra3.service.IEmpleadoService;

@Service
@Primary
public class EmpleadoServiceImplJpa implements IEmpleadoService {

    @Autowired
    EmpleadoRepository repo;

    @Override
    public List<Empleado> buscar() {
        return repo.findAll();
    }

    @Override
    public Empleado buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Empleado guardar(Empleado empleado) {
        return repo.save(empleado);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public boolean existe(Integer id) {
        return repo.existsById(id);
    }

    @Override
    public Empleado buscarPorDni(Integer dni) {
        return repo.getByDni(dni);
    }

    @Override
    public Empleado activar_desactivar(Integer id) {
        Empleado empleado = repo.findById(id).orElse(null);
        if (empleado != null) {
            empleado.setActivo(!empleado.isActivo());  // Cambia el estado de "activo"
            repo.save(empleado);  // Guarda el cambio en la base de datos
        }
        return empleado;
    }

    @Override
    public List<Empleado> buscarSupervisadosPorId(Integer id) {
        return repo.findBySupervisorId(id);
    }

   
    @Override
    public Empleado validarYGuardarEmpleado(Empleado empleado) throws Exception {

        // Condición 1: Verifica que el correo electrónico no esté vacío y que cumpla con las restricciones de tamaño
        if (empleado.getCorreoElectronico() == null || empleado.getCorreoElectronico().isEmpty()) {
            throw new Exception("El correo electrónico no puede estar vacío.");
        }
        if (empleado.getCorreoElectronico().length() > 40) {
            throw new Exception("El correo electrónico no debe superar los 40 caracteres.");
        }

        // Condición 2: Asegúrate de que el empleado esté activo (activo = true)
        if (!empleado.isActivo()) {
            throw new Exception("El empleado debe estar activo.");
        }

        // Condición 3: Verifica que el supervisor asociado exista y esté activo
        if (empleado.getSupervisor() != null && empleado.getSupervisor().getId() != null) {
            // Verifica que el supervisor esté activo
            boolean supervisorActivo = repo.existsByIdAndActivoTrue(empleado.getSupervisor().getId());
            if (!supervisorActivo) {
                throw new Exception("El supervisor debe existir y estar activo.");
            }
        }

        // Si todas las validaciones pasan, guarda el empleado
        return repo.save(empleado);
    }
}
