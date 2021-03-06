package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.validator.RivenditoreValidator;
import com.example.demo.model.Accessorio;
import com.example.demo.model.Rivenditore;
import com.example.demo.service.AccessorioService;
import com.example.demo.service.RivenditoreService;

@Controller
public class RivenditoreController {
	
	@Autowired
	private RivenditoreService rivenditoreService;
	
	@Autowired
	private RivenditoreValidator rivenditoreValidator;
	
	@Autowired
	private AccessorioService accessorioService;
	

	
	
	@PostMapping("/admin/rivenditore")
	public String addRivenditore(@Valid @ModelAttribute("rivenditore") Rivenditore r, @RequestParam(name = "accessorioScelto") Long id, BindingResult bindingResult, Model model){
		this.rivenditoreValidator.validate(r, bindingResult);
		if(!bindingResult.hasErrors()) {
			Accessorio a = this.accessorioService.searchById(id);
			r.setAccessorio(a);
			a.getRivenditoriDellAccessorio().add(r);
			this.accessorioService.inserisci(a);
			model.addAttribute("rivenditore", r);
			return "rivenditore.html";
			
		}
		
		model.addAttribute("rivenditore", r);
		return "rivenditoreForm.html";
	}
	
	@GetMapping("/admin/rivenditoreForm")
	public String getRivenditoreForm(Model model) {
		model.addAttribute("rivenditore", new Rivenditore());
		model.addAttribute("accessoriDisponibili", this.accessorioService.findAllAccessori());
		return "rivenditoreForm.html";
	}
	
	@GetMapping("/admin/deleteRivenditore")
	public String deleteRivenditore(@RequestParam Long rivenditoreId) {
		this.rivenditoreService.rimuovi(rivenditoreId);
		return "redirect:/elencoRivenditori";
	}
	
	@GetMapping("/elencoRivenditori")
	public String getAllRivenditori(Model model) {
		List<Rivenditore> elencoRivenditori = this.rivenditoreService.findAllRivenditori();
		model.addAttribute("elencoRivenditori", elencoRivenditori);
		return "elencoRivenditori.html";
	}
	
	@GetMapping("/rivenditore/{id}")
	public String getRivenditore(@PathVariable("id") Long id, Model model) {
		Rivenditore rivenditore = this.rivenditoreService.searchById(id);
		model.addAttribute("rivenditore", rivenditore);
		return "rivenditore.html";
	}

}
