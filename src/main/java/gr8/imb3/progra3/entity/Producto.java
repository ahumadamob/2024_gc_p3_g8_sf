package gr8.imb3.progra3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Producto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String Descripcion;
	private String CodigoDeBarra;
	private Boolean Disponibilidad;
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	private Integer cantidad;
	
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
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public Boolean getDisponibilidad() {
		return Disponibilidad;
	}
	public void setDisponibilidad(Boolean disponibilidad) {
		Disponibilidad = disponibilidad;
	}
	public String getCodigoDeBarra() {
		return CodigoDeBarra;
	}
	public void setCodigoDeBarra(String codigoDeBarra) {
		CodigoDeBarra = codigoDeBarra;
	}
	

}
