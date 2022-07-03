package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Rivenditore {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nomeStore;
	
	private String URL;
	
	private String prezzo;
	
	@ManyToOne
	private Accessorio accessorio;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeStore() {
		return nomeStore;
	}

	public void setNomeStore(String nomeStore) {
		this.nomeStore = nomeStore;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(String prezzo) {
		this.prezzo = prezzo;
	}

	public Accessorio getAccessorio() {
		return accessorio;
	}

	public void setAccessorio(Accessorio accessorio) {
		this.accessorio = accessorio;
	}
}
