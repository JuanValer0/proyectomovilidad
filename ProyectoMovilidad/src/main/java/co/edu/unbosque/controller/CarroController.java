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

import co.edu.unbosque.model.Carro;
import co.edu.unbosque.model.Persona;
import co.edu.unbosque.repository.CarroRepository;
import co.edu.unbosque.repository.PersonaRepository;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Juanapi")
public class CarroController {

	@Autowired
	private CarroRepository Carepo;
	private PersonaRepository Perepo;

	@PostMapping("/Carro")
	public ResponseEntity<String> agregarCarro(@RequestParam String nom, @RequestParam String cedula,
			@RequestParam String sexo, @RequestParam String email, @RequestParam String placa,
			@RequestParam String marca) {
		
		Carro car = new Carro();
		car.setMarca(marca);
		car.setPlaca(placa);
		car.setPropietario(agregarPersona(nom, cedula, email, sexo));
		Carepo.save(car);
		return ResponseEntity.status(HttpStatus.CREATED).body("Incidente creado con éxito 201");

	}

	@PostMapping("/propietario")
	public Persona agregarPersona(@RequestParam String nom, @RequestParam String cedula, @RequestParam String sexo,
			@RequestParam String email) {
		Persona per = new Persona();
		per.setNom(nom);
		per.setCedula(cedula);
		per.setSexo(sexo);
		per.setEmail(email);
		Perepo.save(per);
		return per;

	}

	@GetMapping("/carro")
	public ResponseEntity<Iterable<Carro>> listarTodosLosCarros() {
		List<Carro> all = (List<Carro>) Carepo.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}

	@PutMapping("/carro/placa/{placa}")
	public ResponseEntity<String> actualizarPorPlaca(@RequestParam String placa, @RequestParam String marca,
			@RequestParam String nom, @RequestParam String cedula, @RequestParam String sexo,
			@RequestParam String email) {
		Optional<Carro> dato = Carepo.findByPlaca(placa);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dato no encontrado");
		}
		Carro dat = dato.get();
		dat.setMarca(marca);
		dat.setPropietario(agregarPersona(nom, cedula, sexo, email));
		Carepo.save(dat);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dato actualizado con éxito");
	}

	@DeleteMapping("/carro/{id}")
	public ResponseEntity<String> eliminarPorID(@RequestParam String id) {
		Integer id1 = Integer.parseInt(id);
		Optional<Carro> dato = Carepo.findById(id1);
		if (dato.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se puede eliminar el dato");
		}
		Carepo.deleteById(id1);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Agente eliminado con éxito");
	}

	@GetMapping("/carro/{placa}")
	public ResponseEntity<Optional<Carro>> mostarPorPlaca(@PathVariable String placa) {
		Optional<Carro> op = Carepo.findByPlaca(placa);
		if (op.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(op);
	}

}
