package it.uniroma3.SIW.progettoCatering.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.SIW.progettoCatering.model.Piatto;
import it.uniroma3.SIW.progettoCatering.repository.PiattoRepository;

@Service
public class PiattoService {
	
	@Autowired
	private PiattoRepository piattoRepository;

	@Transactional
	public void save(Piatto piatto) {
		piattoRepository.save(piatto);
	}
	
	public Piatto findById(Long id) {
		return piattoRepository.findById(id).get();
	}
	
	public List<Piatto> findAll(){
		List<Piatto> piatti = new ArrayList<Piatto>();
		for(Piatto p : piattoRepository.findAll()) {
			piatti.add(p);
		}
		return piatti;
	}

	public boolean alreadyExists(Piatto piatto) {
		return piattoRepository.existsByNomeAndDescrizione(piatto.getNome(), piatto.getDescrizione());
	}

	public void remove(Long id) {
		piattoRepository.deleteById(id);
	}

}
