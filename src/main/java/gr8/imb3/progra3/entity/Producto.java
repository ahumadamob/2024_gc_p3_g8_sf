package gr8.imb3.progra3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity

public class Producto  extends BaseEntity  {
	@NotNull
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	@Size(max = 40, message = "La descripción no debe superar los 40 caracteres ni debe ser menor a 1 caracter")

	private String descripcion;
	@NotBlank
	@Size(min= 1, max = 40, message = "El código de barra no debe superar los 40 caracteres ni debe ser menor a 1 caracter")
	private String codigoDeBarra;
	@NotNull
	private Boolean disponibilidad;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	@NotNull
	private Integer cantidad;
	@NotNull
	private Double precio;
	
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer idProducto) {
		this.id = idProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getDisponibilidad() {
		return disponibilidad;
	}
	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
	public String getCodigoDeBarra() {
		return codigoDeBarra;
	}
	public void setCodigoDeBarra(String codigoDeBarra) {
		this.codigoDeBarra = codigoDeBarra;
	}

	

}
