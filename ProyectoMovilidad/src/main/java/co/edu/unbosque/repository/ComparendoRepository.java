package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.model.Comparendo;


public interface ComparendoRepository extends  CrudRepository<Comparendo, Integer> {
	
	public List<Comparendo> findAll();
	public Optional<Comparendo> findBypropietario(String nom);
	public Optional<Comparendo> findByPlaca(String placa);
	public Optional<Comparendo> findById(Integer id);

}
