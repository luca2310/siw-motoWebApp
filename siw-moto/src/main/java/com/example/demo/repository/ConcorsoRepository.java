package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Concorso;

public interface ConcorsoRepository extends CrudRepository<Concorso, Long> {

	public Concorso findByNome(String nome);
}
