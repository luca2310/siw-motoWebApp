package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.controller.validator.CredentialsValidator;
import com.example.demo.controller.validator.UserValidator;
import com.example.demo.model.Accessorio;
import com.example.demo.model.Credentials;
import com.example.demo.model.Marca;
import com.example.demo.model.Moto;
import com.example.demo.model.Rivenditore;
import com.example.demo.model.User;
import com.example.demo.service.*;

@Controller
public class AuthenticationController {

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private CredentialsValidator credentialsValidator;
	
//	@Autowired
//	private MarcaService mas;
//	
//	@Autowired
//	private MotoService mos;
//	
//	@Autowired
//	private AccessorioService as;
//	
//	@Autowired
//	private RivenditoreService rs;

	
	
	@GetMapping("/register")
	public String showRegisterForm(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credentials", new Credentials());
		return "register.html";
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		return "login.html";
	}

	@GetMapping("/logout")
	public String logout(Model model) {
		return "index";
	}

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultAfterLogin(Model model) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
			return "adminDefault.html"; //adminDashboard ???
		}
		return "index";
	}
	
	
//	@GetMapping("/admin/features")
//	public String features(Model model) {
//		List<Marca> elencoMarche = this.mas.findAllMarche();
//		List<Moto> elencoMoto = this.mos.findAllMoto();
//		List<Accessorio> elencoAccessorio = this.as.findAllAccessori();
//		List<Rivenditore> elencoRivenditori = this.rs.findAllRivenditori();
//		model.addAttribute("elencoMarche", elencoMarche);
//		model.addAttribute("elencoMoto", elencoMoto);
//		model.addAttribute("elencoAccessorio", elencoAccessorio);
//		model.addAttribute("elencoRivenditori", elencoRivenditori);
//		return "adminFeatures.html";
//	}

	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult userBindingResult,
			@ModelAttribute("credentials") Credentials credentials, BindingResult credentialsBindingResult,
			Model model) {

		// validate user and credentials fields
		this.userValidator.validate(user, userBindingResult);
		this.credentialsValidator.validate(credentials, credentialsBindingResult);

		System.out.println(userBindingResult);

		// if neither of them had invalid contents, store the User and the Credentials into the DB
		if (!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
			// set the user and store the credentials;
			// this also stores the User, thanks to Cascade.ALL policy
			credentials.setUser(user);
			credentialsService.saveCredentials(credentials);
			return "registrationCompleted.html";
		}
		return "register.html";
	}
}
