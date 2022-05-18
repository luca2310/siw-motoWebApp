package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Accessorio;
import it.uniroma3.siw.repository.AccessorioRepository;

@Service
public class AccessorioService {
	
	@Autowired
	private AccessorioRepository accessorioRepository;
	
	public Accessorio inserisci(Accessorio accessorio) {
		
		return this.accessorioRepository.save(accessorio);
	}

}
