package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.model.Persona;

public interface PersonaRepository extends CrudRepository <Persona, Integer> {
	
	public List<Persona> findAll();
	public Optional<Persona> findByNom(String nom);
	public Optional<Persona> findByCedula(String cedula);
	public Optional<Persona> findById(Integer id);
	public Optional<Persona> eliminarById(Integer id);
	
}
