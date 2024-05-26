package gr8.imb3.progra3.entity;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;
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
	@JoinColumn(name = "idProveedor")
	private Proveedor proveedor;
	@JoinColumn(name = "fecha")
	private Date fecha;
	@JoinColumn(name = "hora")
	private Time hora;
	@JoinColumn(name = "descripcion")
	private String descripcion;
	@JoinColumn(name = "precio_unitario")
	private float precio_unitario;
	@JoinColumn(name = "factura")
	private int factura;
	@JoinColumn(name = "cantidad_antes")
	private int cantidad_antes;
	@JoinColumn(name = "cantidad_despues")
	private int cantidad_despues;
	
	public enum TipoMovimiento {
	    COMPRA,
	    VENTA,
	    TRANSFERENCIA,
	    AJUSTE
	}
	
	public Integer getIdMovimiento() {
		return idMovimiento;
	}
	public void setIdMovimiento(Integer idMovimiento) {
		this.idMovimiento = idMovimiento;
	}
	public TipoMovimiento getTipo() {
		return tipoMovimiento;
	}
	public void setTipo(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio_unitario() {
		return precio_unitario;
	}
	public void setPrecio_unitario(float precio_unitario) {
		this.precio_unitario = precio_unitario;
	}
	public int getFactura() {
		return factura;
	}
	public void setFactura(int factura) {
		this.factura = factura;
	}
	public int getCantidad_antes() {
		return cantidad_antes;
	}
	public void setCantidad_antes(int cantidad_antes) {
		this.cantidad_antes = cantidad_antes;
	}
	public int getCantidad_despues() {
		return cantidad_despues;
	}
	public void setCantidad_despues(int cantidad_despues) {
		this.cantidad_despues = cantidad_despues;
	}
	
}
