package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Moto;
import com.example.demo.service.MotoService;

@Component
public class MotoValidator implements Validator {
	
	@Autowired
	private MotoService motoService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.motoService.alreadyExists((Moto) o)) {
			errors.reject("moto.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Moto.class.equals(aClass);
	}


}
