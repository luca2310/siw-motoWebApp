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
public class Moto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String modello;
	
//	private String colore;
	
	private Long anno;
	
	@ManyToOne
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	@OneToMany(mappedBy = "moto", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
	private List<Accessorio> accessoriDellaMoto;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}
	
//	public String getColore() {
//		return colore;
//	}
//	
//	public void setColore(String colore) {
//		this.colore = colore;
//	}

	public Long getAnno() {
		return anno;
	}

	public void setAnno(Long anno) {
		this.anno = anno;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Accessorio> getAccessoriDellaMoto() {
		return accessoriDellaMoto;
	}

	public void setAccessoriDellaMoto(List<Accessorio> accessori) {
		this.accessoriDellaMoto = accessori;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		Moto moto = (Moto) obj;
		return (this.modello.equals(moto.getModello()) 
				&& this.anno.equals(moto.getAnno()));
	}
}
