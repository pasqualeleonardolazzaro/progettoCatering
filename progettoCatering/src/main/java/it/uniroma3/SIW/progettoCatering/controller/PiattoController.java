package it.uniroma3.SIW.progettoCatering.controller;

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

import it.uniroma3.SIW.progettoCatering.model.Buffet;
import it.uniroma3.SIW.progettoCatering.model.Ingrediente;
import it.uniroma3.SIW.progettoCatering.model.Piatto;
import it.uniroma3.SIW.progettoCatering.service.BuffetService;
import it.uniroma3.SIW.progettoCatering.service.IngredienteService;
import it.uniroma3.SIW.progettoCatering.service.PiattoService;
import it.uniroma3.SIW.progettoCatering.validator.PiattoValidator;

@Controller
public class PiattoController {
	
	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PiattoValidator piattoValidator;

	@PostMapping("/piattiForm")
	public String addPiatto(@Valid @ModelAttribute("piatto") Piatto piatto, BindingResult bindingResults, Model model) {
		piattoValidator.validate(piatto,  bindingResults);
		if(!bindingResults.hasErrors()) {
			piattoService.save(piatto);
			model.addAttribute("buffet", piatto);
			return "redirect:/piattiForm";
		}
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		List<Buffet> listBuffets = buffetService.findAll();
		model.addAttribute("listBuffets", listBuffets);
		List<Ingrediente> listIngredients = ingredienteService.findAll();
		model.addAttribute("listIngredients", listIngredients);
		return "piatto/piattiForm.html";
	}

	@PostMapping("/cancellaPiatto/{id}")
	public String removePiatto(@PathVariable("id") Long id, Model model) {
		piattoService.remove(id);
		return  "redirect:/piattiForm";
	}


	@GetMapping("/piattiForm")
	public String getPiatti(Model model) {
		model.addAttribute("piatto", new Piatto());
		List<Piatto> piatti = piattoService.findAll();
		model.addAttribute("piatti", piatti);
		List<Buffet> listBuffets = buffetService.findAll();
		model.addAttribute("listBuffets", listBuffets);
		List<Ingrediente> listIngredients = ingredienteService.findAll();
		model.addAttribute("listIngredients", listIngredients);
		return "piatto/piattiForm.html";
	}
	
	@GetMapping("/ingredienti/{id}")
	public String getPiattoPerBuffet(@PathVariable("id") Long id, Model model) {
		  Piatto piatto = piattoService.findById(id);
		  model.addAttribute("piatto", piatto);
		  return "piatto/ingredientiPerPiatto.html";
	  }

}
