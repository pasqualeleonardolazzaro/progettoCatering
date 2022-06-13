package it.uniroma3.SIW.progettoCatering.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.SIW.progettoCatering.model.Ingrediente;
import it.uniroma3.SIW.progettoCatering.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long> {

	boolean existsByNomeAndDescrizione(String nome, String descrizione);
	
	public  List<Piatto> findByIngredienti(Ingrediente ingrediente);

}
