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
	private String cantidad;
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	
	
	public Integer getIdProducto() {
		return id;
	}
	public void setIdProducto(Integer idProducto) {
		this.id = idProducto;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	

}
