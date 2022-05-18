package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Moto;
import it.uniroma3.siw.repository.MotoRepository;

@Service
public class MotoService {
	
	@Autowired
	private MotoRepository motoRepository;
	
	public Moto inserisci(Moto moto) {
		
		return this.motoRepository.save(moto);
	}

}
