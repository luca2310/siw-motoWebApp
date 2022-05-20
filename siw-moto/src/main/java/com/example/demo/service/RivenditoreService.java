package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Rivenditore;
import com.example.demo.repository.RivenditoreRepository;

@Service
public class RivenditoreService {
	
	@Autowired
	private RivenditoreRepository rivenditoreRepository;
	
	public Rivenditore inserisci(Rivenditore rivenditore) {
		
		return this.rivenditoreRepository.save(rivenditore);
	}

}
