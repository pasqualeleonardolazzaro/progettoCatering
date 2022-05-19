package it.uniroma3.SIW.progettoCatering.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.SIW.progettoCatering.model.Ingrediente;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long> {

	boolean existsByNomeAndOrigineAndDescrizione(String nome, String origine, String descrizione);

}
