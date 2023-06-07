package co.edu.unbosque.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import co.edu.unbosque.model.Persona;
import co.edu.unbosque.repository.PersonaRepository;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Juanapi")
public class PersonaController {
	
	@Autowired
	private PersonaRepository Perepo;
	
	@PostMapping("/persona")
	public ResponseEntity<String> agregarPersona(@RequestParam String nom,@RequestParam String cedula,@RequestParam String sexo,@RequestParam String email){
		Persona per = new Persona();
		per.setNom(nom);
		per.setCedula(cedula);
		per.setSexo(sexo);
		per.setEmail(email);
		Perepo.save(per);
		return ResponseEntity.status(HttpStatus.CREATED).body("Agente agregado con éxito (CODE 201)\n");
		
	}
	@GetMapping("/persona")
	public ResponseEntity<Iterable<Persona>> listarLasPersonas() {
		List<Persona> all = (List<Persona>) Perepo.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}
	
	@GetMapping("/persona/{id}")
	public ResponseEntity<Optional<Persona>> mostarUnaPersona(@PathVariable Integer id) {
		Optional<Persona> op = Perepo.findById(id);
		if (op.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(op);
	}
	
	@GetMapping("/persona/{cedula}")
	public ResponseEntity<Optional<Persona>> mostarPorCedula(@PathVariable String cedula) {
		Optional<Persona> op = Perepo.findByCedula(cedula);
		if (op.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(op);
	}
	
	@PutMapping("/persona/cedula/{cedula}")
	public ResponseEntity<String> actualizarPorCedula(@RequestParam String cedula,@RequestParam String sexo, @RequestParam String nom, @RequestParam String email) {
		Optional<Persona> dato = Perepo.findByCedula(cedula);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dato no encontrado");
		}
		Persona temp = dato.get();
		temp.setNom(nom);
		temp.setCedula(cedula);
		temp.setSexo(sexo);
		temp.setEmail(email);
		Perepo.save(temp);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dato actualizado con éxito");
	}
	@DeleteMapping("/agente/{id}")
	public ResponseEntity<String> eliminarPorID(@RequestParam String id) {
		Integer id1 = Integer.parseInt(id);
		Optional<Persona> dato = Perepo.findById(id1);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede eliminar el dato");
		}
		Perepo.deleteById(id1);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Agente eliminado con éxito");
	}
}
