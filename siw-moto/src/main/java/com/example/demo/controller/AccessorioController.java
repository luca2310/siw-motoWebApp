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

import com.example.demo.controller.validator.AccessorioValidator;
import com.example.demo.model.Accessorio;
import com.example.demo.model.Moto;
import com.example.demo.service.AccessorioService;
import com.example.demo.service.MotoService;

@Controller
public class AccessorioController {
	
	@Autowired
	private AccessorioService accessorioService;
	
	@Autowired
	private AccessorioValidator accessorioValidator;
	
	@Autowired
	private MotoService motoService;
	
	
	
	
	@PostMapping("/admin/accessorio")
	public String addAccessorio(@Valid @ModelAttribute("accessorio") Accessorio a, @RequestParam(name = "motoScelta") Long id, BindingResult bindingResult, Model model) {
		this.accessorioValidator.validate(a, bindingResult);
		if(!bindingResult.hasErrors()) {
			Moto m = this.motoService.searchById(id);
			a.setMoto(m);
			m.getAccessoriDellaMoto().add(a);
			this.motoService.inserisci(m);
			model.addAttribute("accessorio", a);
			model.addAttribute("elencoRivenditori", a.getRivenditoriDellAccessorio());
			return "accessorio.html";
		}
		model.addAttribute("accessorio", a);
		return "accessorioForm.html";
	}
	
	@GetMapping("/admin/accessorioForm")
	public String getAccessorioForm(Model model) {
		model.addAttribute("accessorio", new Accessorio());
		model.addAttribute("motoDisponibili", this.motoService.findAllMoto());
		return "accessorioForm.html";
	}
	
	@GetMapping("/admin/deleteAccessorio")
	public String deleteAccessorio(@RequestParam Long accessorioId) {
		this.accessorioService.rimuovi(accessorioId);
		return "redirect:/elencoAccessori";
	}
	
	@GetMapping("/elencoAccessori")
	public String getAllAccessori(Model model) {
		List<Accessorio> elencoAccessori = this.accessorioService.findAllAccessori();
		model.addAttribute("elencoAccessori", elencoAccessori);
		return "elencoAccessori.html";
	}
	
	@GetMapping("/accessorio/{id}")
	public String getPiatto(@PathVariable("id") Long id, Model model) {
		Accessorio accessorio = this.accessorioService.searchById(id);
		model.addAttribute("accessorio", accessorio);
		model.addAttribute("elencoRivenditori", accessorio.getRivenditoriDellAccessorio());
		return "accessorio.html";
	}


}
