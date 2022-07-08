package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Concorso;
import com.example.demo.repository.ConcorsoRepository;

@Service
public class ConcorsoService {

	@Autowired
	private ConcorsoRepository concorsoRepository;
	
	public Concorso inserisci(Concorso concorso) {
		
		return this.concorsoRepository.save(concorso);
	}

	public boolean alreadyExists(Concorso c) {
		return this.findAllConcorsi().contains(c);
	}
	
	@Transactional
	public void rimuovi(Long id) {
		this.concorsoRepository.deleteById(id);
	}
	
	@Transactional
	public void clear() {
		this.concorsoRepository.deleteAll();
	}
	
	public Concorso searchById(Long id) {
		return this.concorsoRepository.findById(id).get();
	}
	
	public List<Concorso> findAllConcorsi(){
		List<Concorso> elencoConcorsi = new ArrayList<Concorso>();
		for(Concorso c : this.concorsoRepository.findAll()) {
			elencoConcorsi.add(c);
		}
		return elencoConcorsi;
	}
}
