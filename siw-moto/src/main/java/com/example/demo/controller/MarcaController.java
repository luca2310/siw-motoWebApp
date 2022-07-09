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

import com.example.demo.controller.validator.MarcaValidator;
import com.example.demo.model.Marca;
import com.example.demo.service.MarcaService;

@Controller
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;
	
	@Autowired
	private MarcaValidator marcaValidator;

	

	@PostMapping("/admin/marca")
	public String addMarca(@Valid @ModelAttribute("marca") Marca m, BindingResult bindingResult, Model model) {
		this.marcaValidator.validate(m, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.marcaService.inserisci(m);
			model.addAttribute("marca", this.marcaService.searchById(m.getId()));
			model.addAttribute("elencoMoto", m.getMotoDellaMarca());
			return "marca.html";
		}
		else {
			return "marcaForm.html";
		}
	}
	
	@GetMapping("/admin/marcaForm")
	public String getMarcaForm(Model model) {
		model.addAttribute("marca", new Marca());
		return "marcaForm.html";
	}
	
	@GetMapping("/elencoMarche")
	public String getAllMarche(Model model) {
		List<Marca> elencoMarche = this.marcaService.findAllMarche();
		model.addAttribute("elencoMarche", elencoMarche);
		return "elencoMarche.html";
	}
	
	@GetMapping("/marca/{id}")
	public String getMarca(@PathVariable("id") Long id, Model model) {
		Marca marca = this.marcaService.searchById(id);
		model.addAttribute("marca", marca);
		model.addAttribute("elencoMoto", marca.getMotoDellaMarca());
		return "marca.html";
	}
	
	@GetMapping("/admin/deleteMarca")
	public String deleteMarca(@RequestParam Long marcaId) {
		this.marcaService.rimuovi(marcaId);
		return "redirect:/elencoMarche";
	}
}
