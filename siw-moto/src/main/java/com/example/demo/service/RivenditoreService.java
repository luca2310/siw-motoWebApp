package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Rivenditore;
import com.example.demo.repository.RivenditoreRepository;

@Service
public class RivenditoreService {
	
	@Autowired
	private RivenditoreRepository rivenditoreRepository;
	
	public Rivenditore inserisci(Rivenditore rivenditore) {
		
		return this.rivenditoreRepository.save(rivenditore);
	}

	public boolean alreadyExists(Rivenditore r) {
		return this.findAllRivenditori().contains(r);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.rivenditoreRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.rivenditoreRepository.deleteAll();
	}
	
	public Rivenditore searchById(Long id) {
		return this.rivenditoreRepository.findById(id).get();
	}
	
	public List<Rivenditore> findAllRivenditori(){
		List<Rivenditore> elencoRivenditori= new ArrayList<Rivenditore>();
		for(Rivenditore r : this.rivenditoreRepository.findAll()) {
			elencoRivenditori.add(r);
		}
		return elencoRivenditori;
	}

}
