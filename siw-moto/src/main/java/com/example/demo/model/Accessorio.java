package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Accessorio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	
	private String descrizione;
	
	private String tipo;
	
	@ManyToOne
	@JoinColumn(name = "moto_id")
	private Moto moto;
	
	@OneToMany(mappedBy = "accessorio", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private List<Rivenditore> rivenditoriDellAccessorio;
	
	

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

	public Moto getMoto() {
		return moto;
	}

	public void setMoto(Moto moto) {
		this.moto = moto;
	}

	public List<Rivenditore> getRivenditoriDellAccessorio() {
		return rivenditoriDellAccessorio;
	}

	public void setRivenditoriDellAccessorio(List<Rivenditore> rivenditori) {
		this.rivenditoriDellAccessorio = rivenditori;
	}
	
	@Override
	public boolean equals(Object obj) {
		Accessorio accessorio = (Accessorio) obj;
		return this.nome.equals(accessorio.getNome())
				&& this.tipo.equals(accessorio.getTipo());
	}
}
