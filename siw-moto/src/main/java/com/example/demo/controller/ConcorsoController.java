package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.controller.validator.ConcorsoValidator;
import com.example.demo.model.Concorso;
import com.example.demo.service.ConcorsoService;

public class ConcorsoController {

	@Autowired
	private ConcorsoService concorsoService;
	
	@Autowired
	private ConcorsoValidator concorsoValidator;

	

	@PostMapping("/admin/concorso")
	public String addConcorso(@Valid @ModelAttribute("concorso") Concorso c, BindingResult bindingResult, Model model) {
		this.concorsoValidator.validate(c, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.concorsoService.inserisci(c);
			model.addAttribute("concorso", this.concorsoService.searchById(c.getId()));
			model.addAttribute("elencoMoto", c.getMotoDelConcorso());
			return "concorso.html";
		}
		else {
			return "concorsoForm.html";
		}
	}
	
	@GetMapping("/admin/concorsoForm")
	public String getConcorsoForm(Model model) {
		model.addAttribute("concorso", new Concorso());
		return "concorsoForm.html";
	}
	
	@GetMapping("/elencoConcorsi")
	public String getAllConcorsi(Model model) {
		List<Concorso> elencoConcorsi = this.concorsoService.findAllConcorsi();
		model.addAttribute("elencoConcorsi", elencoConcorsi);
		return "elencoConcorsi.html";
	}
	
	@GetMapping("/concorso/{id}")
	public String getConcorso(@PathVariable("id") Long id, Model model) {
		Concorso concorso = this.concorsoService.searchById(id);
		model.addAttribute("concorso", concorso);
		model.addAttribute("elencoMoto", concorso.getMotoDelConcorso());
		return "concorso.html";
	}
	
	@GetMapping("/admin/deleteConcorso")
	public String deleteConcorso(@RequestParam Long concorsoId) {
		this.concorsoService.rimuovi(concorsoId);
		return "redirect:/elencoConcorsi";
	}
	
	
	@GetMapping("/admin/updateConcorso")
    private String updateConcorsoForm(@RequestParam Long concorsoId, Model model) {
        model.addAttribute("concorso", this.concorsoService.searchById(concorsoId));
        return "concorsoUpdateForm.html";
    }

    @PostMapping("/admin/concorsoUpdate/{id}")
    private String updateConcorso(@Valid @ModelAttribute("concorso") Concorso concorso, BindingResult bindingResult, Model model) {

        this.concorsoValidator.validate(concorso, bindingResult);
        if (!bindingResult.hasErrors()) {
            model.addAttribute("concorso", concorso);
            model.addAttribute("elencoMoto", concorso.getMotoDelConcorso());
            return "concorso.html";
        }
        model.addAttribute("concorso", concorso);
        return "concorsoUpdateForm.html";
    }
}
