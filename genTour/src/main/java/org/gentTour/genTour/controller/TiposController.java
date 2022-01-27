package org.gentTour.genTour.controller;
/*
 * 
 * @author Júlia Inoscência O. dos Santos
 * @since 22/01/2022
 * @version 0.02
  */

/*
 * @author Guilherme Barbosa Rodrigues
 * @since 26/01/2022
 * @version 0.03
 */

import java.util.List;

import org.gentTour.genTour.model.Tipos;
import org.gentTour.genTour.repository.TiposRepository;
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
@RequestMapping("/tipo") // Tipos de Turismo
@CrossOrigin("*")
public class TiposController {
	@Autowired
	private TiposRepository repository;

	@GetMapping // Pesquisar por todos os tipos de turismo na região.
	public ResponseEntity<List<Tipos>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}") // Pesquisa para ADMIN caso precisar de Hotfix.
	public ResponseEntity<Tipos> findByTipoTurismo(@PathVariable(value = "id") long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElseGet(() -> {
					throw new ResponseStatusException(HttpStatus.NO_CONTENT, "ID não Encontrado");
		});
	}
	
	@GetMapping("/tipo/{tipoTurismo}") // Pesquisa por tipo 
	public ResponseEntity<List<Tipos>> GetByTipoTurismo(@PathVariable String tipoTurismo){
		return ResponseEntity.ok(repository.findAllByTipoTurismoContainingIgnoreCase(tipoTurismo));
	}
	
	@GetMapping("/tipo/{local}") // Pesquisa por Local
	public ResponseEntity<List<Tipos>> GetByLocal(@PathVariable String local){
		return ResponseEntity.ok(repository.findAllByLocalContainingIgnoreCase(local));
	}
	
	@GetMapping("/tipo/{temporada}") // Pesquisa por Temporada 
	public ResponseEntity<List<Tipos>> GetByTemporada(@PathVariable String temporada){
		return ResponseEntity.ok(repository.findAllByTemporadaContainingIgnoreCase(temporada));
	}

	
	@PostMapping("/save") // Adicionar novo tipo de Postagem
	public ResponseEntity<Tipos> saveTipoTurismo(@RequestBody Tipos newTipos){
		return ResponseEntity.status(201).body(repository.save(newTipos));
	}
	
	/*
	 * @author Darllan Lopes Pinto
	 * @since 24/01/2022
	 * @version 0.03
	  */
	
	@PutMapping("/update")
	public ResponseEntity<Tipos> updateTipoTurismo (@RequestBody Tipos tipos){ 
		return ResponseEntity.status(200).body(repository.save(tipos));
	}
 
	@DeleteMapping("/{id}")
	public void deleteTipos (@PathVariable long id) {
		repository.deleteById(id);
	}
	
}


