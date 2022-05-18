package it.uniroma3.SIW.progettoCatering.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.SIW.progettoCatering.model.Chef;

public interface ChefRepository extends CrudRepository<Chef, Long> {
	public boolean existsByNomeAndCognomeAndNazionalita(String nome,String cognome, String nazionalita);
}
