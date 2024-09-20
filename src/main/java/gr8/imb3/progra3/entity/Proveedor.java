package gr8.imb3.progra3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Proveedor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "El nombre no puede estar vacío")
	@Size(max = 50, message = "El nombre no debe superar los 50 caracteres") 
	private String  nombre;
	
	@NotNull(message = "El estado habilitado no puede ser nulo")
    private boolean habilitado;
	
    @NotBlank(message = "La dirección no puede estar vacía")
    private String direccion;
    
    @NotNull(message = "El teléfono no puede ser nulo")
    @Positive(message = "El número de teléfono debe ser positivo")
    private Integer telefono;
    
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    private String correoElectronico;

    @NotNull(message = "El CUIL no puede ser nulo")
    @Positive(message = "El CUIL debe ser un número positivo")
    private Integer cuil;

    @NotBlank(message = "El nombre del proveedor de contacto no puede estar vacío")
    private String personaContacto;
    private String comentario;
    
	@ManyToMany
	@JoinTable(name="categoria_proveedor", joinColumns = {@JoinColumn(name = "proveedor_id")}, inverseJoinColumns = {@JoinColumn(name = "categoria_id")})
	private Set<Categoria> categorias = new HashSet<>();

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
	public boolean getHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public Integer getCuil() {
		return cuil;
	}
	public void setCuil(Integer cuil) {
		this.cuil = cuil;
	}
	public String getPersonaContacto() {
		return personaContacto;
	}
	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Set<Categoria> getCategoria() {
		return categorias;
	}
	public void setCategoria(Set<Categoria> categoria) {
		this.categorias.addAll(categoria);
	}
}