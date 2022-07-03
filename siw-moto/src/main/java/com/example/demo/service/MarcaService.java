package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Marca;
import com.example.demo.repository.MarcaRepository;

@Service
public class MarcaService {
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	public Marca inserisci(Marca marca) {
		
		return this.marcaRepository.save(marca);
	}

	public boolean alreadyExists(Marca m) {
		return this.findAllMarche().contains(m);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.marcaRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.marcaRepository.deleteAll();
	}
	
	public Marca searchById(Long id) {
		return this.marcaRepository.findById(id).get();
	}
	
	public List<Marca> findAllMarche(){
		List<Marca> elencoMarche = new ArrayList<Marca>();
		for(Marca m : this.marcaRepository.findAll()) {
			elencoMarche.add(m);
		}
		return elencoMarche;
	}

}
