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
import it.uniroma3.SIW.progettoCatering.service.BuffetService;
import it.uniroma3.SIW.progettoCatering.service.ChefService;
import it.uniroma3.SIW.progettoCatering.validator.ChefValidator;


@Controller
public class ChefController {
	@Autowired
	private ChefService chefService;

	@Autowired
	private ChefValidator chefValidator;

	@PostMapping("/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		chefValidator.validate(chef,  bindingResults);
		if(!bindingResults.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", chef);
			return "redirect:/chef";
		}
		List<Chef>chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chef.html";
	}

	@PostMapping("/cancellaChef/{id}")
	public String removePersona(@PathVariable("id") Long id, Model model) {
		chefService.remove(id);
		return  "redirect:/chef";
	}


	@GetMapping("/chef")
	public String getChef(Model model) {
		model.addAttribute("chef", new Chef());
		List<Chef>chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chef.html";
	}
	
	//id tra graffe perchè indica un parametro
	  @GetMapping("/buffet/{id}")
	  public String getBuffetPerChef(@PathVariable("id") Long id, Model model) {
		  Chef chef = chefService.findById(id);
		  model.addAttribute("chef", chef);
		  return "buffetPerChef.html";
	  }

}
