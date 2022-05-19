package it.uniroma3.SIW.progettoCatering.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.SIW.progettoCatering.model.Buffet;

public interface BuffetRepository extends CrudRepository<Buffet, Long> {
	public boolean existsByNomeAndDescrizione(String nome,String descrizione);
}
