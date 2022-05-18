package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Rivenditore;
import it.uniroma3.siw.repository.RivenditoreRepository;

@Service
public class RivenditoreService {
	
	@Autowired
	private RivenditoreRepository rivenditoreRepository;
	
	public Rivenditore inserisci(Rivenditore rivenditore) {
		
		return this.rivenditoreRepository.save(rivenditore);
	}

}
