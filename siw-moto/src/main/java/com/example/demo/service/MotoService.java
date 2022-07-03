package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Moto;
import com.example.demo.repository.MotoRepository;

@Service
public class MotoService {
	
	@Autowired
	private MotoRepository motoRepository;
	
	public Moto inserisci(Moto moto) {
		
		return this.motoRepository.save(moto);
	}
	
	public boolean alreadyExists(Moto m) {
		return this.findAllMoto().contains(m);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.motoRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.motoRepository.deleteAll();
	}
	
	public Moto searchById(Long id) {
		return this.motoRepository.findById(id).get();
	}
	
	public List<Moto> findAllMoto(){
		List<Moto> elencoMoto = new ArrayList<Moto>();
		for(Moto m : this.motoRepository.findAll()) {
			elencoMoto.add(m);
		}
		return elencoMoto;
	}

}
