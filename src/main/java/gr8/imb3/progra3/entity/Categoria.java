package gr8.imb3.progra3.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.HashSet;

@Entity
public class Categoria extends BaseEntity{

	@NotBlank(message = "El nombre no puede estar vacio")
	@Size(max = 40, message = "El nombre no debe superar los 40 caracteres")
	private String nombre;

	private boolean habilitado;
	@ManyToMany(mappedBy = "categorias")
	private Set<Proveedor> proveedores = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "ramo_id", nullable = false)
	private Ramo ramo;


	public Set<Proveedor> getProveedores() {
		return proveedores;
	}
	public void setProveedores(Set<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}
	public Ramo getRamo() {
		return ramo;
	}
	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}
	//nombre
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//habilitado
	public boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
}
