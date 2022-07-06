package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Accessorio;
import com.example.demo.repository.AccessorioRepository;

@Service
public class AccessorioService {
	
	@Autowired
	private AccessorioRepository accessorioRepository;
	
	public Accessorio inserisci(Accessorio accessorio) {
		
		return this.accessorioRepository.save(accessorio);
	}

	public boolean alreadyExists(Accessorio a) {
		return this.findAllAccessori().contains(a);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.accessorioRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.accessorioRepository.deleteAll();
	}
	
	public Accessorio searchById(Long id) {
		return this.accessorioRepository.findById(id).get();
	}
	
	public List<Accessorio> findAllAccessori(){
		List<Accessorio> elencoAccessori = new ArrayList<Accessorio>();
		for(Accessorio a : this.accessorioRepository.findAll()) {
			elencoAccessori.add(a);
		}
		return elencoAccessori;
	}

}
