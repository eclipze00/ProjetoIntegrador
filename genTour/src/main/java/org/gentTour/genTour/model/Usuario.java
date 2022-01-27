package org.gentTour.genTour.model;

import java.util.List;

import javax.persistence.CascadeType;

/*
 * @autor Wesley Barreto Coelho
 * @Date 27/01/2022
 * @Version 0.05
 * */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String nome;
	
	@NotBlank
	@Email(message = "Por favor entre com um Email valido")
	private String email;
	
	@NotBlank
	private String senha;
	
	public List<Tipos> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipos> tipos) {
		this.tipos = tipos;
	}

	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	@JsonIgnoreProperties
	private List<Tipos> tipos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
	
}
