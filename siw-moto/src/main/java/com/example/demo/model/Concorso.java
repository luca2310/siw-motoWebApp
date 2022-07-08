package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Concorso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String descrizione;
	
	@OneToMany(mappedBy = "concorso", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<Moto> motoDelConcorso;

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

	public List<Moto> getMotoDelConcorso() {
		return motoDelConcorso;
	}

	public void setMotoDelConcorso(List<Moto> motoDelConcorso) {
		this.motoDelConcorso = motoDelConcorso;
	}
	
	
	
	
}
