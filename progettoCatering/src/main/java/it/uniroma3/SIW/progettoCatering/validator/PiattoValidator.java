package it.uniroma3.SIW.progettoCatering.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.SIW.progettoCatering.model.Piatto;
import it.uniroma3.SIW.progettoCatering.service.PiattoService;

@Component
public class PiattoValidator implements Validator{
	
	@Autowired
	private PiattoService piattoService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Piatto.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (this.piattoService.alreadyExists((Piatto) o)) {
			errors.reject("piatto.duplicato");
		}
	}
}
