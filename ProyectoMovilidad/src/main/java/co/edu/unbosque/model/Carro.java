package co.edu.unbosque.model;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String marca;
    private String placa;

    @ManyToOne
    private Persona propietario;

    @OneToMany(mappedBy = "comparendo")
    private List<Comparendo> comparendos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Persona getPropietario() {
		return propietario;
	}

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}

	public List<Comparendo> getComparendos() {
		return comparendos;
	}

	public void setComparendos(List<Comparendo> comparendos) {
		this.comparendos = comparendos;
	}
    


    
}