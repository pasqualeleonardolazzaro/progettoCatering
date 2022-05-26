package it.uniroma3.SIW.progettoCatering;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.SIW.progettoCatering.model.Buffet;
import it.uniroma3.SIW.progettoCatering.model.Chef;
import it.uniroma3.SIW.progettoCatering.model.Ingrediente;
import it.uniroma3.SIW.progettoCatering.model.Piatto;
import it.uniroma3.SIW.progettoCatering.repository.BuffetRepository;
import it.uniroma3.SIW.progettoCatering.repository.ChefRepository;
import it.uniroma3.SIW.progettoCatering.repository.IngredienteRepository;
import it.uniroma3.SIW.progettoCatering.repository.PiattoRepository;

@SpringBootApplication
public class ProgettoCateringApplication{

	public static void main(String[] args) {
		SpringApplication.run(ProgettoCateringApplication.class, args);
	}

}
