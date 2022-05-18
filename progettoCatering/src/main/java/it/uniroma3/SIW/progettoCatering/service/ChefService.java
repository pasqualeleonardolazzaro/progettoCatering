package it.uniroma3.SIW.progettoCatering.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.SIW.progettoCatering.model.Chef;
import it.uniroma3.SIW.progettoCatering.repository.ChefRepository;

@Service
public class ChefService {
	@Autowired
	private ChefRepository chefRepository;

	@Transactional
	public void save(Chef chef) {
		chefRepository.save(chef);
	}
	
	public Chef findById(Long id) {
		return chefRepository.findById(id).get();
	}
	
	public boolean alreadyExists(Chef chef) {
		return chefRepository.existsByNomeAndCognomeAndNazionalita(chef.getNome(), chef.getCognome(), chef.getNazionalita());
	}
	
	public List<Chef> findAll(){
		List<Chef> chefs = new ArrayList<Chef>();
		for(Chef c : chefRepository.findAll()) {
			chefs.add(c);
		}
		return chefs;
	}

	public void remove(Long id) {
		chefRepository.deleteById(id);
	}
}
