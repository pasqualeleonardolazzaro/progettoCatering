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

	@PostMapping("/admin/chef")
	public String addChef(@Valid @ModelAttribute("chef") Chef chef, BindingResult bindingResults, Model model) {
		chefValidator.validate(chef,  bindingResults);
		if(!bindingResults.hasErrors()) {
			chefService.save(chef);
			model.addAttribute("chef", chef);
			return "redirect:/admin/chef";
		}
		List<Chef>chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chef/chef.html";
	}

	@PostMapping("/admin/cancellaChef/{id}")
	public String removeChef(@PathVariable("id") Long id, Model model) {
		chefService.remove(id);
		return  "redirect:/admin/chef";
	}


	@GetMapping("/admin/chef")
	public String getChef(Model model) {
		model.addAttribute("chef", new Chef());
		List<Chef>chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chef/chef.html";
	}
	@GetMapping("/chef")
	public String getChefList(Model model) {
		model.addAttribute("chef", new Chef());
		List<Chef>chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chef/chefList.html";
	}
	
	//id tra graffe perchè indica un parametro
	  @GetMapping("/admin/buffet/{id}")
	  public String getBuffetPerChef(@PathVariable("id") Long id, Model model) {
		  Chef chef = chefService.findById(id);
		  model.addAttribute("chef", chef);
		  return "chef/buffetPerChef.html";
	  }
	  
	//id tra graffe perchè indica un parametro
	  @GetMapping("/buffet/{id}")
	  public String getBuffetPerChefList(@PathVariable("id") Long id, Model model) {
		  Chef chef = chefService.findById(id);
		  model.addAttribute("chef", chef);
		  return "chef/buffetPerChefList.html";
	  }
	  
	  @GetMapping("/admin/chefEdit/{id}")
	  public String editChef(@PathVariable("id") Long id, Model model) {
		  model.addAttribute("chef", chefService.findById(id));
		  return "chef/chefEdit.html";
	  }
	  
	  @PostMapping("/admin/chefEdit/{id}")
		public String editChefForm(@Valid @ModelAttribute("chef") Chef chef,BindingResult bindingResults, @PathVariable("id") Long id, Model model) {
			chefValidator.validate(chef,  bindingResults);
			if(!bindingResults.hasErrors()) {
				chefService.save(chef);
				return "redirect:/admin/chef";
			}
			List<Chef>chefs = chefService.findAll();
			model.addAttribute("chefs", chefs);
			return "chef/chefEdit.html";
		}

}
