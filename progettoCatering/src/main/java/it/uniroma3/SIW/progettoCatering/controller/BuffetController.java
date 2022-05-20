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
import it.uniroma3.SIW.progettoCatering.model.Chef;
import it.uniroma3.SIW.progettoCatering.service.BuffetService;
import it.uniroma3.SIW.progettoCatering.service.ChefService;
import it.uniroma3.SIW.progettoCatering.validator.BuffetValidator;

@Controller
public class BuffetController {
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetValidator buffetValidator;

	@PostMapping("/buffetForm")
	public String addBuffet(@Valid @ModelAttribute("buffet") Buffet buffet, BindingResult bindingResults, Model model) {
		buffetValidator.validate(buffet,  bindingResults);
		if(!bindingResults.hasErrors()) {
			buffetService.save(buffet);
			model.addAttribute("buffet", buffet);
			return "redirect:/buffetForm";
		}
		
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffets", buffets);
		List<Chef> listChefs = chefService.findAll();
		model.addAttribute("listChefs", listChefs);
		return "buffetForm.html";
	}

	@PostMapping("/cancellaBuffet/{id}")
	public String removeBuffet(@PathVariable("id") Long id, Model model) {
		buffetService.remove(id);
		return  "redirect:/buffetForm";
	}


	@GetMapping("/buffetForm")
	public String getBuffet(Model model) {
		model.addAttribute("buffet", new Buffet());
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffets", buffets);
		List<Chef> listChefs = chefService.findAll();
		model.addAttribute("listChefs", listChefs);
		return "buffetForm.html";
	}
}
