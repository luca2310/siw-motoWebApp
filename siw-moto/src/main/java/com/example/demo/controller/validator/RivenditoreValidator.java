package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Rivenditore;
import com.example.demo.service.RivenditoreService;

@Component
public class RivenditoreValidator implements Validator {
	
	@Autowired
	private RivenditoreService rivenditoreService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.rivenditoreService.alreadyExists((Rivenditore) o)) {
			errors.reject("marca.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Rivenditore.class.equals(aClass);
	}


}
