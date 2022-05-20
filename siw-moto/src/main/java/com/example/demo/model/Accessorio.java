package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Accessorio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	
	private String descrizione;
	
	private String tipo;
	
	@ManyToMany
	private List<Moto> moto;
	
	@OneToMany(mappedBy = "accessorio", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private List<Rivenditore> rivenditori;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Moto> getMoto() {
		return moto;
	}

	public void setMoto(List<Moto> moto) {
		this.moto = moto;
	}

	public List<Rivenditore> getRivenditori() {
		return rivenditori;
	}

	public void setRivenditori(List<Rivenditore> rivenditori) {
		this.rivenditori = rivenditori;
	}
}
