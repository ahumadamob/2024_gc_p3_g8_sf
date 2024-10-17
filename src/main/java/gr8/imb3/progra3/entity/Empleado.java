package gr8.imb3.progra3.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Empleado extends BaseEntity {

	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres")
	private String nombre;
	private String tipoPuesto;
	private	Integer dni;
	@NotBlank(message = "El Correo Electronico no puede estar vacío")
	@Size(max = 40, message = "El Correo Electronico no debe superar los 40 caracteres")
	private String correoElectronico;
	@NotBlank(message = "El teléfono no puede estar vacío")
	@Size(max = 20, message = "El numero no debe superar los 20 caracteres")
	private String telefono;
	private boolean activo;
	@ManyToOne
	@JoinColumn(name = "supervisor")
	private Empleado supervisor;
	private Date fechaContratacion;


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoPuesto() {
		return tipoPuesto;
	}

	public void setTipoPuesto(String tipoPuesto) {
		this.tipoPuesto = tipoPuesto;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Empleado getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Empleado supervisor) {
		this.supervisor = supervisor;
	}

	public Date getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Date fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

}
