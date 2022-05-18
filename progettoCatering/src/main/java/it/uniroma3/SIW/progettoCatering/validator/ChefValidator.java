package it.uniroma3.SIW.progettoCatering.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.SIW.progettoCatering.model.Chef;
import it.uniroma3.SIW.progettoCatering.service.ChefService;

@Component
public class ChefValidator implements Validator {
	
	@Autowired
	private ChefService chefService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Chef.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		if (this.chefService.alreadyExists((Chef) o)) {
			errors.reject("chef.duplicato");
		}
	}

}
