package org.generation.genTour.controller;
/* 
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

import org.generation.genTour.model.Tipos;
import org.generation.genTour.repository.TiposRepository;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
	
	@GetMapping("/categoria/{categoria}") // Pesquisa por tipo 
	public ResponseEntity<List<Tipos>> GetByCategoria(@PathVariable String categoria){
		return ResponseEntity.ok(repository.findAllByCategoriaContainingIgnoreCase(categoria));
	}
	
	
	@GetMapping("/descricao/{descricao}") // Pesquisa por descrição 
	public ResponseEntity<List<Tipos>> GetByDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	
	@PostMapping("/save") // Adicionar novo tipo de Postagem
	public ResponseEntity<Tipos> saveCategoria (@RequestBody Tipos newCategoria){
		return ResponseEntity.status(201).body(repository.save(newCategoria));
	}
	
	/*
	 * @author Darllan Lopes Pinto
	 * @since 24/01/2022
	 * @version 0.03
	  */
	
	@PutMapping("/update")
	public ResponseEntity<Tipos> updateCategoria (@RequestBody Tipos categoria){ 
		return ResponseEntity.status(200).body(repository.save(categoria));
	}
 
	@DeleteMapping("/{id}")
	public void deleteTipos (@PathVariable long id) {
		repository.deleteById(id);
	}
	
}


