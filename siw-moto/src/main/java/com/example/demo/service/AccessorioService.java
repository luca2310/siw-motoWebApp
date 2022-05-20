package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Accessorio;
import com.example.demo.repository.AccessorioRepository;

@Service
public class AccessorioService {
	
	@Autowired
	private AccessorioRepository accessorioRepository;
	
	public Accessorio inserisci(Accessorio accessorio) {
		
		return this.accessorioRepository.save(accessorio);
	}

}
