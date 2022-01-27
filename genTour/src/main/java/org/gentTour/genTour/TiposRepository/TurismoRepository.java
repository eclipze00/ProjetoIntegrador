package org.gentTour.genTour.TiposRepository;

import java.util.List;

import org.gentTour.genTour.model.Turismo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurismoRepository extends JpaRepository<Turismo, Long>{ 
		public List<Turismo>findAllByHospedagemContainingIgnoreCase(String hospedagem);
		public List<Turismo>findAllByAtracaoContainingIgnoreCase(String atracao);
		//public List<Turismo>findAllByPrecoContainingIgnoreCase(double preco);
		
}
