package br.com.seasonjavateam.membersmanagement.controller;

import br.com.seasonjavateam.membersmanagement.model.Person;
import br.com.seasonjavateam.membersmanagement.service.PersonService;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class is PersonController
 * @author danielhidekicassi
 */
@Controller
@RequestMapping("/")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private MessageSource messageSource;

	/*
	 * This method will list all existing persons.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listPerson(ModelMap model) {
		List<Person> persons = personService.findAll();
		model.addAttribute("persons", persons);
		return "allpersons";
	}

	/*
	 * This method will provide the medium to add a new person.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newEmployee(ModelMap model) {
		Person person = new Person();
		model.addAttribute("person", person);
		model.addAttribute("edit", false);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving person in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String save(@Valid Person person, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [cpf] should be implementing custom @Unique annotation 
		 * and applying it on field [cpf] of Model class [Person].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!personService.isPersonUnique(person.getId(), person.getCpf())){
			FieldError ssnError =new FieldError("person","cpf",messageSource.getMessage("non.unique.cpf", new String[]{person.getCpf()}, Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}

		personService.save(person);

		model.addAttribute("success", "Person " + person.getName() + " registered successfully");
		return "success";
	}

	/*
	 * This method will provide the medium to update an existing person.
	 */
	@RequestMapping(value = { "/edit-{cpf}-person" }, method = RequestMethod.GET)
	public String editEmployee(@PathVariable String cpf, ModelMap model) {
		Person person = personService.findByCpf(cpf);
		model.addAttribute("person", person);
		model.addAttribute("edit", true);
		return "registration";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating person in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{cpf}-person" }, method = RequestMethod.POST)
	public String updateEmployee(@Valid Person person, BindingResult result,
			ModelMap model, @PathVariable String cpf) {

		if (result.hasErrors()) {
			return "registration";
		}

		if(!personService.isPersonUnique(person.getId(), person.getCpf())){
			FieldError ssnError =new FieldError("person","cpf",messageSource.getMessage("non.unique.cpf", new String[]{person.getCpf()}, Locale.getDefault()));
			result.addError(ssnError);
			return "registration";
		}

		personService.update(person);

		model.addAttribute("success", "Person " + person.getName()  + " updated successfully");
		return "success";
	}

	/*
	 * This method will delete an person by it's CPF value.
	 */
	@RequestMapping(value = { "/delete-{cpf}-person" }, method = RequestMethod.GET)
	public String deleteEmployee(@PathVariable String cpf) {
		personService.deleteByCpf(cpf);
		return "redirect:/list";
	}
}
