package org.gentTour.genTour.controller;
/* 
* @author Júlia Inoscência O. dos Santos
* @since 27/01/2022
* @version 0.05
*/
import java.util.List;
import java.util.Optional;

import org.gentTour.genTour.model.Usuario;
import org.gentTour.genTour.model.UsuarioLogin;
import org.gentTour.genTour.repository.UsuarioRepository;
import org.gentTour.genTour.service.UsuarioService;
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
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired UsuarioService usuarioService;

	
	/**
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<List<Usuario>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findByNome(@PathVariable(value = "id") long id) {
		return repository.findById(id).map(resp -> ResponseEntity.status(200).body(resp)).orElseGet(() -> {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "ID não Encontrado");
		});
	}

	@GetMapping("/{nome}")
	public ResponseEntity<List<Usuario>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@GetMapping("/{email}")
	public ResponseEntity<List<Usuario>> getByEmail(@PathVariable String email) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(email));
	}
	
	/**
	 * 
	 * @return
	 */
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> register (@RequestBody Usuario usuario) {
		return ResponseEntity.status(201)
				.body(usuarioService.cadastrarUsuario(usuario));
				
	}
	
	@PostMapping ("/logar")
	public ResponseEntity<UsuarioLogin> login (@RequestBody Optional <UsuarioLogin> user){
		return usuarioService.login(user)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(401).build());
	}
	
	/**
	 * 
	 * @return
	 */

	@PutMapping("/update")
	public ResponseEntity<Usuario> updateNome(@RequestBody Usuario nome) {
		return ResponseEntity.status(200).body(repository.save(nome));
	}
	
	@PutMapping("/update/email")
	public ResponseEntity<Usuario> updateEmail(@RequestBody Usuario email) {
		return ResponseEntity.status(200).body(repository.save(email));
	}
	
	@PutMapping("/update/senha")
	public ResponseEntity<Usuario> updateSenha(@RequestBody Usuario senha) {
		return ResponseEntity.status(200).body(repository.save(senha));
	}
	
	/**
	 * 
	 * @return
	 */

	@DeleteMapping("/{id}")
	public void deleteNome(@PathVariable long id) {
		repository.deleteById(id);
	}
}


