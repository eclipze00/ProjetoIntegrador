package org.generation.genTour.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.genTour.model.Usuario;
import org.generation.genTour.model.UsuarioLogin;
import org.generation.genTour.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**
	 * 
	 * 
	 */

	public Optional<Usuario> cadastrarUsuario(Usuario email) {

		if (usuarioRepository.findByEmail(email.getEmail()).isPresent())
			return Optional.empty();

		email.setSenha(criptografarSenha(email.getSenha()));

		return Optional.of(usuarioRepository.save(email));

	}
	
	/**
	 * 
	 * 
	 */
	
	public Optional<Usuario> atualizarUsuario(Usuario email) {

		if (usuarioRepository.findById(email.getId()).isPresent()) {

			Optional<Usuario> buscaUsuario = usuarioRepository.findByEmail(email.getEmail());

			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getId() != email.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			email.setSenha(criptografarSenha(email.getSenha()));

			return Optional.ofNullable(usuarioRepository.save(email));

		}

		return Optional.empty();

	}
	
	/**
	 * 
	 * 
	 */
	
	public Optional<UsuarioLogin> login (Optional<UsuarioLogin> emailLogin) {

		Optional<Usuario> usuario = usuarioRepository.findByEmail(emailLogin.get().getEmail());

		if (usuario.isPresent()) {

			if (compararSenhas(emailLogin.get().getSenha(), usuario.get().getSenha())) {

				emailLogin.get().setId(usuario.get().getId());
				emailLogin.get().setNome(usuario.get().getNome());
				emailLogin.get().setFoto(usuario.get().getFoto());
				emailLogin.get()
						.setToken(gerarBasicToken(emailLogin.get().getEmail(), emailLogin.get().getSenha()));
				emailLogin.get().setSenha(usuario.get().getSenha());
				emailLogin.get().setTipo(usuario.get().getTipo());

				return emailLogin;
			}
		}

		return Optional.empty();
	}
	
	/**
	 * 
	 * 
	 */
	
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);
	}
	
	/**
	 * 
	 * 
	 */

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);

	}
	
	/**
	 * 
	 * 
	 */
	
	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

}