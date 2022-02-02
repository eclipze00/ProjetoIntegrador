package org.gentTour.genTour.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.gentTour.genTour.model.Usuario;
import org.gentTour.genTour.model.UsuarioLogin;
import org.gentTour.genTour.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario cadastrarUsuario(Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		return usuarioRepository.save(usuario);
	}
	
	public Optional<UsuarioLogin> login (Optional <UsuarioLogin> user){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional <Usuario> usuario = usuarioRepository.findAllByEmail(user.get().getUsuario());
		
		if(usuario.isPresent()) {
			if(encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {
				
				String auth = user.get().getUsuario()+ ":" + user.get().getSenha();
				byte[] encoderAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic" + new String(encoderAuth);
				
					user.get().setToken(authHeader);
					user.get().setNome(usuario.get().getNome());
					return user;
			}
		}
		return null;
	}
}
