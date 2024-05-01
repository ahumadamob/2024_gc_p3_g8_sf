package gr8.imb3.progra3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
public class Movimiento {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idMovimiento;
	@Min(value = 0, message = "El ´tipo´ debe ser mayor o igual a 0")
	private int tipo;
	@ManyToOne
	@JoinColumn(name = "idCategoria")
	private Categoria categoria;
	@Max(value = 100, message = "La mayor cantidad de productos a vender o comprar es de 100")
	private int cantidad;
	@ManyToOne
	@JoinColumn(name = "idProducto")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name = "idEmpleado")
	private Empleado empleado;
	@ManyToOne
	@JoinColumn(name = "idPoriveedor")
	private Proveedor proveedor;
	public Integer getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	
}
