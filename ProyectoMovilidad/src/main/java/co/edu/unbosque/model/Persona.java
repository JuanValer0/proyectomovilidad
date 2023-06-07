package co.edu.unbosque.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String cedula;
    private String sexo;
    private String Email;

    @OneToMany(mappedBy = "propietario")
    private List<Carro> carros;

    @OneToMany(mappedBy = "persona")
    private List<Comparendo> comparendos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	public List<Comparendo> getComparendos() {
		return comparendos;
	}

	public void setComparendos(List<Comparendo> comparendos) {
		this.comparendos = comparendos;
	}
	
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String Email) {
		this.Email = Email;
	}

    
}
   