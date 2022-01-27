package org.gentTour.genTour.repository;

/*
 * @autor Wesley Barreto Coelho
 * @Date 27/01/2022
 * @Version 0.05
 * */

import java.util.List;

import org.gentTour.genTour.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

//@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	public List<Usuario>findAllByNomeContainingIgnoreCase(String nome);
	
	public List<Usuario>findAllByEmailContainingIgnoreCase(String email);
	
}
