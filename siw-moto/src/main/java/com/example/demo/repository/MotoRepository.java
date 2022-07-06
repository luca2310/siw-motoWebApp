package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Moto;

public interface MotoRepository extends CrudRepository<Moto, Long> {

	//public Moto findByNomeAndAnno(String nome, Long anno);//
}
