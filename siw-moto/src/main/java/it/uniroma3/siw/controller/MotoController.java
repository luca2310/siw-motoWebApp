package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.controller.validator.MotoValidator;
import it.uniroma3.siw.service.MotoService;

@Controller
public class MotoController {
	
	@Autowired
	private MotoService motoService;
	
	@Autowired
	private MotoValidator motoValidator;

}
