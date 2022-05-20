package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.demo.controller.validator.RivenditoreValidator;
import com.example.demo.service.RivenditoreService;

@Controller
public class RivenditoreController {
	
	@Autowired
	private RivenditoreService rivenditoreService;
	
	@Autowired
	private RivenditoreValidator rivenditoreValidator;

}
