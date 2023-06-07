package co.edu.unbosque.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import co.edu.unbosque.model.Carro;

public interface CarroRepository extends CrudRepository<Carro, Integer>{
	
	public List<Carro> findAll();
	public Optional<Carro> findByPlaca(String placa);
	public Optional<Carro> findByid(Integer id);
	public Optional<Carro> findByNom(String nom);

}
