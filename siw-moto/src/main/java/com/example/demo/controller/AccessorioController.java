package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.controller.validator.AccessorioValidator;
import com.example.demo.service.AccessorioService;

@Controller
public class AccessorioController {
	
	@Autowired
	private AccessorioService accessorioService;
	
	@Autowired
	private AccessorioValidator accessorioValidator;

}
