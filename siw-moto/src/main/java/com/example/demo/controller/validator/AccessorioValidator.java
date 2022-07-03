package com.example.demo.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.demo.model.Accessorio;
import com.example.demo.service.AccessorioService;

@Component
public class AccessorioValidator implements Validator {
	
	@Autowired
	private AccessorioService accessorioService;
	
	@Override
	public void validate(Object o, Errors errors) {
		if(this.accessorioService.alreadyExists((Accessorio) o)) {
			errors.reject("accessorio.duplicato");
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Accessorio.class.equals(aClass);
	}


}
