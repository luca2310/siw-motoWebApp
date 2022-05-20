package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.controller.validator.MotoValidator;
import com.example.demo.service.MotoService;

@Controller
public class MotoController {
	
	@Autowired
	private MotoService motoService;
	
	@Autowired
	private MotoValidator motoValidator;

}
