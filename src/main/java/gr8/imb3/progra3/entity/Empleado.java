package gr8.imb3.progra3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Empleado {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres")
	private String nombre;
	private String tipoPuesto;
	private	Integer dni;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

}
