package org.generation.genTour.controller;

/*
 * @author Guilherme Barbosa Rodrigues
 * @since 27/01/2022
 * @version 0.05
 * 
 */

import java.util.List;

import org.generation.genTour.model.Turismo;
import org.generation.genTour.repository.TurismoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/turismo")
@CrossOrigin("*")
public class TurismoController {
	
	@Autowired
	private TurismoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Turismo>> GetAll () {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turismo> findByHospedagem(@PathVariable(value = "id") long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.NO_CONTENT, "ID não encontrado");
				});
	}
	
	@GetMapping("/turismo/{hospedagem}")
	public ResponseEntity<List<Turismo>> getByHospedagem(@PathVariable String hospedagem){
		return ResponseEntity.ok(repository.findAllByHospedagemContainingIgnoreCase(hospedagem));
	}
	
	@GetMapping("/turismo/{atracao}")
	public ResponseEntity<List<Turismo>> getByAtracao(@PathVariable String atracao){
		return ResponseEntity.ok(repository.findAllByAtracaoContainingIgnoreCase(atracao));
	}
	
	@PostMapping("/save")
	public ResponseEntity<Turismo> saveHospedagem(@RequestBody Turismo newTurismo){
		return ResponseEntity.status(201).body(repository.save(newTurismo));
	}
	
	@PutMapping("/update")
	public ResponseEntity<Turismo> updateHospedagem(@RequestBody Turismo turismo){
		return ResponseEntity.status(200).body(repository.save(turismo));
	}
	
	@DeleteMapping("/{id}")
	public void deleteTurismo (@PathVariable long id) {
		repository.deleteById(id);
	}

}
