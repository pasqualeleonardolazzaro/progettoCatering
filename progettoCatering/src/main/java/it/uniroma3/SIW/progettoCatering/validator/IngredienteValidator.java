package it.uniroma3.SIW.progettoCatering.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.SIW.progettoCatering.model.Ingrediente;
import it.uniroma3.SIW.progettoCatering.service.IngredienteService;

@Component
public class IngredienteValidator implements Validator {
	
	@Autowired
	private IngredienteService ingredienteService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Ingrediente.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (this.ingredienteService.alreadyExists((Ingrediente) o)) {
			errors.reject("ingrediente.duplicato");
		}
	}

}
