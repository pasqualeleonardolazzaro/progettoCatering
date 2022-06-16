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

import it.uniroma3.SIW.progettoCatering.model.Ingrediente;
import it.uniroma3.SIW.progettoCatering.service.IngredienteService;
import it.uniroma3.SIW.progettoCatering.validator.IngredienteValidator;

@Controller
public class IngredienteController {

	@Autowired
	private IngredienteService ingredienteService;

	@Autowired
	private IngredienteValidator ingredienteValidator;

	@PostMapping("/admin/ingredienteForm")
	public String addPiatto(@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResults, Model model) {
		ingredienteValidator.validate(ingrediente,  bindingResults);
		if(!bindingResults.hasErrors()) {
			ingredienteService.save(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			return "redirect:/admin/ingredientiForm";
		}
		List<Ingrediente>ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingrediente/ingredientiForm.html";
	}

	@PostMapping("/admin/cancellaIngrediente/{id}")
	public String removeIngrediente(@PathVariable("id") Long id, Model model) {
		ingredienteService.remove(id);
		return  "redirect:/admin/ingredientiForm";
	}


	@GetMapping("/admin/ingredientiForm")
	public String getIngrediente(Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
		List<Ingrediente>ingredienti = ingredienteService.findAll();
		model.addAttribute("ingredienti", ingredienti);
		return "ingrediente/ingredientiForm.html";
	}


	@GetMapping("/admin/editIngrediente/{id}")
	public String editIngredienteForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ingrediente",ingredienteService.findById(id));		
		return "ingrediente/ingredienteEditForm.html";
	}
	
	@PostMapping("/admin/editIngrediente/{id}")
	public String editIngredienteForm(@PathVariable("id") Long id,@Valid @ModelAttribute("ingrediente") Ingrediente ingrediente, BindingResult bindingResult, Model model) {
		ingredienteValidator.validate(ingrediente, bindingResult);
		if(!bindingResult.hasErrors()) {
			ingredienteService.save(ingrediente);
			return "redirect:/admin/ingredientiForm";
		}
		return "ingrediente/ingredienteEditForm.html";
	}
	
}