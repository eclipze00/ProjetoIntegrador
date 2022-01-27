package org.gentTour.genTour.model;

/*
 * @author Guilherme Barbosa Rodrigues
 * @since 21/01/2022
 * @version 0.00
 * 
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tiposTurismo")
public class Tipos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String tipoTurismo;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String local;
	
	@NotBlank
	@Size(min = 5, max = 100)
	private String temporada;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTipoTurismo() {
		return tipoTurismo;
	}

	public void setTipoTurismo(String tipoTurismo) {
		this.tipoTurismo = tipoTurismo;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

}
