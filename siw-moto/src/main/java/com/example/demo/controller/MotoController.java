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

import com.example.demo.controller.validator.MotoValidator;
import com.example.demo.model.Marca;
import com.example.demo.model.Moto;
import com.example.demo.service.MarcaService;
import com.example.demo.service.MotoService;

@Controller
public class MotoController {
	
	@Autowired
	private MotoService motoService;
	
	@Autowired
	private MotoValidator motoValidator;
	
	@Autowired
	private MarcaService marcaService;
	
	
	
	@PostMapping("/admin/moto")
	public String addMoto(@Valid @ModelAttribute("moto") Moto m, BindingResult bindingResult, @RequestParam(name = "marcaScelta") Long id, Model model) {
		
		this.motoValidator.validate(m, bindingResult);
		
		if (!bindingResult.hasErrors()) {
			Marca ma = this.marcaService.searchById(id);
			m.setMarca(ma);
			ma.getMotoDellaMarca().add(m);
			this.marcaService.inserisci(ma);
			
			
			model.addAttribute("moto", m);
			model.addAttribute("elencoAccessori", m.getAccessoriDellaMoto());
			return "moto.html";

		} 
		model.addAttribute("moto", m);
		return "motoForm.html";
	}

	@GetMapping("/admin/motoForm")
	public String getMotoForm(Model model) {
		model.addAttribute("moto", new Moto());
		model.addAttribute("marcheDisponibili", this.marcaService.findAllMarche());
		return "motoForm.html";
	}
	
	@GetMapping("/elencoMoto")
	public String getAllMoto(Model model) {
		List<Moto> elencoMoto = this.motoService.findAllMoto();
		model.addAttribute("elencoMoto", elencoMoto);
		return "elencoMoto.html";
	}
	
	@GetMapping("/moto/{id}")
	public String getMoto(@PathVariable("id") Long id, Model model) {
		Moto moto = this.motoService.searchById(id);
		model.addAttribute("moto", moto);
		model.addAttribute("elencoAccessori", moto.getAccessoriDellaMoto());
		return "moto.html";
	}
	
	@GetMapping("/admin/deleteMoto")
	public String deleteMoto(@RequestParam Long motoId) {
		this.motoService.rimuovi(motoId);
		return "redirect:/elencoMoto";
	}
	

	@GetMapping("/admin/updateMoto")
    private String updateMotoForm(@RequestParam Long motoId, Model model) {
        model.addAttribute("moto", this.motoService.searchById(motoId));
        model.addAttribute("marcheDisponibili", this.marcaService.findAllMarche());
        return "motoUpdateForm.html";
    }

    @PostMapping("/admin/motoUpdate/{id}")
    private String updateMoto(@Valid @ModelAttribute("moto") Moto moto, 
    		@RequestParam(name = "marcaScelta") Long marcaId,
            BindingResult bindingResult,
            Model model) {

        this.motoValidator.validate(moto, bindingResult);
        if (!bindingResult.hasErrors()) {
            Marca marcaNuova = this.marcaService.searchById(marcaId);
            Marca marcaVecchia = moto.getMarca();

            if(marcaVecchia!=null) {
                for(Moto m : marcaVecchia.getMotoDellaMarca()) {
                    if(m.getId() == moto.getId()) {
                        marcaVecchia.getMotoDellaMarca().remove(m);
                    }
                }
            }
            moto.setMarca(marcaNuova);
            marcaNuova.getMotoDellaMarca().add(moto);
            this.marcaService.inserisci(marcaNuova);
            model.addAttribute("moto", moto);
            model.addAttribute("elencoAccessori", moto.getAccessoriDellaMoto());
            return "moto.html";
        }
        model.addAttribute("moto", moto);
        return "motoUpdateForm.html";
    }

}
