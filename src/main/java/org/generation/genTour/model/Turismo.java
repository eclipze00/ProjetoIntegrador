package org.generation.genTour.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name= "Turismo")
public class Turismo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(max = 5000)
	private String foto;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String temporada;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String hospedagem;
	
	@NotBlank
	@Size(min = 3, max = 3000)
	private String descricao;
	
	@NotBlank
	@Size(min = 3, max = 200)
	private String atracao;
	
	@NotNull 
	private double preco; 
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String locomocao;
	
	@ManyToOne
	@JsonIgnoreProperties("Turismo")
	private Tipos tipos;
	
	@ManyToOne
	@JsonIgnoreProperties("Turismo")
	private Usuario usuario;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getTemporada() {
		return temporada;
	}

	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}

	public String getHospedagem() {
		return hospedagem;
	}

	public void setHospedagem(String hospedagem) {
		this.hospedagem = hospedagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAtracao() {
		return atracao;
	}

	public void setAtracao(String atracao) {
		this.atracao = atracao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getLocomocao() {
		return locomocao;
	}

	public void setLocomocao(String locomocao) {
		this.locomocao = locomocao;
	}

	public Tipos getTipos() {
		return tipos;
	}

	public void setTipos(Tipos tipos) {
		this.tipos = tipos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
