package sv.com.taller.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the repuesto database table.
 * 
 */
@Entity
@NamedQuery(name="Repuesto.findAll", query="SELECT r FROM Repuesto r")
public class Repuesto implements Serializable {
	private static final long serialVersionUID = 1L;

	public Repuesto() {}
	
	public Repuesto(Repuesto repuesto) {}
	
	public Repuesto(String idRepuesto, int cantidad, float costo, Date fechaAdquisicion, String nombre,
			float precioVenta, ProveedorMarca proveedorMarca) {
		this.idRepuesto = idRepuesto;
		this.cantidad = cantidad;
		this.costo = costo;
		this.fechaAdquisicion = fechaAdquisicion;
		this.nombre = nombre;
		this.precioVenta = precioVenta;
		this.proveedorMarca = proveedorMarca;
	}

	@Id
	@Column(name="ID_REPUESTO")
	private String idRepuesto;

	private int cantidad;

	private float costo;

	private int disponible;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_ADQUISICION")
	private Date fechaAdquisicion;

	private String nombre;

	@Column(name="PRECIO_VENTA")
	private float precioVenta;

	//bi-directional many-to-one association to ProveedorMarca
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID_PROVEEDOR_MARCA")
	private ProveedorMarca proveedorMarca;

	//bi-directional many-to-one association to ServicioRepuesto
	@OneToMany(mappedBy="repuesto")
	private List<ServicioRepuesto> servicioRepuestos;

	

	public String getIdRepuesto() {
		return this.idRepuesto;
	}

	public void setIdRepuesto(String idRepuesto) {
		this.idRepuesto = idRepuesto;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getCosto() {
		return this.costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public int getDisponible() {
		return this.disponible;
	}

	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}

	public Date getFechaAdquisicion() {
		return this.fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecioVenta() {
		return this.precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public ProveedorMarca getProveedorMarca() {
		return this.proveedorMarca;
	}

	public void setProveedorMarca(ProveedorMarca proveedorMarca) {
		this.proveedorMarca = proveedorMarca;
	}

	public List<ServicioRepuesto> getServicioRepuestos() {
		return this.servicioRepuestos;
	}

	public void setServicioRepuestos(List<ServicioRepuesto> servicioRepuestos) {
		this.servicioRepuestos = servicioRepuestos;
	}

	public ServicioRepuesto addServicioRepuesto(ServicioRepuesto servicioRepuesto) {
		getServicioRepuestos().add(servicioRepuesto);
		servicioRepuesto.setRepuesto(this);

		return servicioRepuesto;
	}

	public ServicioRepuesto removeServicioRepuesto(ServicioRepuesto servicioRepuesto) {
		getServicioRepuestos().remove(servicioRepuesto);
		servicioRepuesto.setRepuesto(null);

		return servicioRepuesto;
	}

}