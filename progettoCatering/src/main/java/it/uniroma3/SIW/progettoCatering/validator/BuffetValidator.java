package it.uniroma3.SIW.progettoCatering.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.SIW.progettoCatering.model.Buffet;
import it.uniroma3.SIW.progettoCatering.service.BuffetService;

@Component
public class BuffetValidator implements Validator{
	
	@Autowired
	private BuffetService buffetService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (this.buffetService.alreadyExists((Buffet) o)) {
			errors.reject("buffet.duplicato");
		}
	}
}
