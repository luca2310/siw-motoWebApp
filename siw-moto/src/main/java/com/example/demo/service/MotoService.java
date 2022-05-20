package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Moto;
import com.example.demo.repository.MotoRepository;

@Service
public class MotoService {
	
	@Autowired
	private MotoRepository motoRepository;
	
	public Moto inserisci(Moto moto) {
		
		return this.motoRepository.save(moto);
	}

}
