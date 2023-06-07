package co.edu.unbosque.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Carro;
import co.edu.unbosque.model.Persona;
import co.edu.unbosque.model.Comparendo;
import co.edu.unbosque.repository.CarroRepository;
import co.edu.unbosque.repository.ComparendoRepository;
import co.edu.unbosque.repository.PersonaRepository;
import jakarta.transaction.Transactional;

@Transactional
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Juanapi")
public class ComparendoController {
	

	@Autowired
	private CarroRepository Carepo;
	private PersonaRepository Perepo;
	private ComparendoRepository Comrepo;
	private SimpleDateFormat sdf;
	
	@PostMapping("/comparendo")
	public ResponseEntity<String> agregarComparendo(@RequestParam String nom,@RequestParam String cedula,@RequestParam String email,@RequestParam String placa,@RequestParam String marca,@RequestParam String codigo,@RequestParam String descripcion,@RequestParam double precio,@RequestParam Date fecha){
		Comparendo com = new Comparendo();
		com.setCodigo(codigo);
		com.setDescripcion(descripcion);
		com.setFecha(fecha);
		com.setprecio(precio);
		com.setPersona(agregarPersona(nom,cedula,email));
		com.setCarro(agregarCarro(nom,cedula, email, marca, placa));
		Comrepo.save(com);
		return ResponseEntity.status(HttpStatus.CREATED).body("Incidente creado con éxito 201");
		
	}
	
	@PostMapping("/propietario")
	public Persona agregarPersona(@RequestParam String nom,@RequestParam String cedula,@RequestParam String email){
		Persona per = new Persona();
		per.setNom(nom);
		per.setcedula(cedula);
		per.setEmail(email);
		Perepo.save(per);
		return per;
		
	}
	@PostMapping("/comcarro")
	public Carro agregarCarro(@RequestParam String nom,@RequestParam String cedula,@RequestParam String email,@RequestParam String placa,@RequestParam String marca ){
		Carro car = new Carro();
		car.setMarca(marca);
		car.setPlaca(placa);
		car.setPropietario(agregarPersona(nom,cedula,email));
		Carepo.save(car);
		return car;
	}
	
	public Date ingresoDeFecha(String yr, String mm, String dd) {
		sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fecha = yr + "/" + mm + "/" + dd;
		Date f = null;
		try {
			f = new Date(sdf.parse(fecha).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return f;
		
	}

	@GetMapping("/comparendos")
	public ResponseEntity<Iterable<Comparendo>> listarTodosLosCarros() {
		List<Comparendo> all = (List<Comparendo>) Comrepo.findAll();
		if (all.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(all);
	}
}
