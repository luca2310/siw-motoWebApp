package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Concorso;
import com.example.demo.service.ConcorsoService;

@Component
public class ConcorsoValidator implements Validator {
	
	@Autowired
	private ConcorsoService concorsoService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.concorsoService.alreadyExists((Concorso) o)) {
			errors.reject("concorso.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Concorso.class.equals(aClass);
	}

}
