package co.edu.unbosque.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Comparendo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;
    private String codigo;
	private String descripcion;
	private double precio;
	private Date fecha;
	 

    @ManyToOne
    private Persona persona;

    @ManyToOne
    private Carro carro;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
 
	public double getprecio() {
		return precio;
	}

	public void setprecio(double precio) {
		this.precio = precio;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

}