package org.generation.genTour.security;

import java.util.Optional;

import org.generation.genTour.model.Usuario;
import org.generation.genTour.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository userRepository;
		
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<Usuario>user = userRepository.findByEmail(userName);
		
		user.orElseThrow(()->new UsernameNotFoundException(userName + "E-mail não encontrado"));
			return user.map(UserDetailsImpl :: new).get();
		
	}
	

}
