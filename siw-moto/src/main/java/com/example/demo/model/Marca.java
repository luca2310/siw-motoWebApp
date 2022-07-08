package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Marca {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String origine;
	
	@OneToMany(mappedBy = "marca", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private List<Moto> motoDellaMarca;
	
	
	

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

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public List<Moto> getMotoDellaMarca() {
		return motoDellaMarca;
	}

	public void setMotoDellaMarca(List<Moto> moto) {
		this.motoDellaMarca = moto;
	}

	@Override
	public boolean equals(Object obj) {
		Marca marca = (Marca) obj;
		return this.nome.equals(marca.getNome());
	}
}
