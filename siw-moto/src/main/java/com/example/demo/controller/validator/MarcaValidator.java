package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Marca;
import com.example.demo.service.MarcaService;

@Component
public class MarcaValidator implements Validator {
	
	@Autowired
	private MarcaService marcaService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.marcaService.alreadyExists((Marca) o)) {
			errors.reject("marca.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Marca.class.equals(aClass);
	}

}
