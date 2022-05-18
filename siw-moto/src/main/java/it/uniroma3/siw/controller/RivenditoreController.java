package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.controller.validator.RivenditoreValidator;
import it.uniroma3.siw.service.RivenditoreService;

@Controller
public class RivenditoreController {
	
	@Autowired
	private RivenditoreService rivenditoreService;
	
	@Autowired
	private RivenditoreValidator rivenditoreValidator;

}
