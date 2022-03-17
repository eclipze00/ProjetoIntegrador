package org.generation.genTour.model;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tiposTurismo")
public class Tipos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 5, max = 300)
	private String categoria;
	
	@NotBlank
	@Size(min = 5, max = 5000)
	private String foto;
	
	@NotBlank
	@Size(min = 5, max = 2000)
	private String descricao;
	
	
	
	@OneToMany(mappedBy = "tipos", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("tipos")
	private List<Turismo> turismo;

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Turismo> getTurismo() {
		return turismo;
	}

	public void setTurismo(List<Turismo> turismo) {
		this.turismo = turismo;
	}

	

	public String getFoto() {
		return foto;
	}


	public void setFoto(String foto) {
		this.foto = foto;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
}
