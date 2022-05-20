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

import it.uniroma3.SIW.progettoCatering.model.Chef;
import it.uniroma3.SIW.progettoCatering.model.Ingrediente;
import it.uniroma3.SIW.progettoCatering.service.IngredienteService;
import it.uniroma3.SIW.progettoCatering.validator.IngredienteValidator;

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private IngredienteValidator ingredienteValidator;

	@PostMapping("/ingredienteForm")
	public String addChef(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
		ingredienteValidator.validate(ingrediente,  bindingResults);
		if(!bindingResults.hasErrors()) {
			ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "redirect:/ingredientiForm";
		}
		List<Ingrediente>ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredientiForm.html";
	}

	@PostMapping("/cancellaIngrediente/{id}")
	public String removePersona(@PathVariable("id") Long id, Model model) {
		ingredienteService.remove(id);
		return  "redirect:/ingredientiForm";
	}


	@GetMapping("/ingredientiForm")
	public String getChef(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		List<Ingrediente>ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingredientiForm.html";
	}

}
