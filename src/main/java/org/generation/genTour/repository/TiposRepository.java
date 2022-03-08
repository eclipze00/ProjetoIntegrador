package org.generation.genTour.repository;

/*
 * @author Wesley Barreto Coelho
 * @since 22/01/2022
 * @version 0.01
 */
import java.util.List;

import org.generation.genTour.model.Tipos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiposRepository extends JpaRepository<Tipos, Long> {

	public List<Tipos> findAllByCategoriaContainingIgnoreCase(String categoria);

	/*
	 * @author Guilherme Barbosa Rodrigues
	 * @since 26/01/2022
	 * @version 0.03
	 * 
	 */
	public List<Tipos> findAllByLocalContainingIgnoreCase(String local);		
	
	public List<Tipos> findAllByDescricaoContainingIgnoreCase(String descricao);

}

