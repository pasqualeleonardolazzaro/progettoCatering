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
public class ProgettoCateringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoCateringApplication.class, args);
	}
	
	@Autowired private ChefRepository chefR;
	@Autowired private IngredienteRepository ingrR;
	@Autowired private BuffetRepository buffR;
	@Autowired private PiattoRepository piattR;
	
	@Override
	public void run(String... args) throws Exception {
		Chef c1 = new Chef();
		c1.setNome("Marcos");
		c1.setCognome("Alonso");
		c1.setNazionalita("Spagna");
		
		Chef c2 = new Chef();
		c2.setNome("Carlo");
		c2.setCognome("Cracco");
		c2.setNazionalita("Italia");
		
		chefR.save(c1);
		chefR.save(c2);
		
		Buffet b1 = new Buffet();
		b1.setNome("Comunione Festiva");
		b1.setDescrizione("Estivo");
		b1.setChef(c1);
		
		Buffet b2 = new Buffet();
		b2.setNome("Comunione Triste");
		b2.setDescrizione("Invernale");
		b2.setChef(c1);
		
		Buffet b3 = new Buffet();
		b3.setNome("Matrimonio");
		b3.setDescrizione("Estivo");
		b3.setChef(c2);
		
		buffR.save(b1);
		buffR.save(b2);
		buffR.save(b3);
		
		Ingrediente i1 = new Ingrediente();
		i1.setNome("Spaghetti");
		i1.setOrigine("Lazio");
		i1.setDescrizione("Paese coltivazione grano: ITALIA");
		
		Ingrediente i2 = new Ingrediente();
		i2.setNome("Mezze Maniche");
		i2.setOrigine("Lazio");
		i2.setDescrizione("Paese coltivazione grano: ITALIA");
		
		Ingrediente i3 = new Ingrediente();
		i3.setNome("Rigatoni");
		i3.setOrigine("Lazio");
		i3.setDescrizione("Paese coltivazione grano: ITALIA");
		
		Ingrediente i4 = new Ingrediente();
		i4.setNome("Sugo");
		i4.setOrigine("Marche");
		i4.setDescrizione("Pomodori italiani");
		
		Ingrediente i5 = new Ingrediente();
		i5.setNome("Pesto");
		i5.setOrigine("Lombardia");
		i5.setDescrizione("Pinoli italiani");
		
		ingrR.save(i1);
		ingrR.save(i2);
		ingrR.save(i3);
		ingrR.save(i4);
		ingrR.save(i5);
		
		Piatto p1 = new Piatto();
		p1.setNome("Carbonara");
		p1.setDescrizione("Al dente");
		p1.setBuffet(b1);
		p1.addIngrediente(i5);
		p1.addIngrediente(i1);
		
		Piatto p2 = new Piatto();
		p2.setNome("Gricia");
		p2.setDescrizione("Cremosa");
		p2.setBuffet(b3);
		p2.addIngrediente(i2);
		p2.addIngrediente(i3);
		
		piattR.save(p1);
		piattR.save(p2);
	}

}
