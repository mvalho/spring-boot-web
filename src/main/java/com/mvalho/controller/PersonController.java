package com.mvalho.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvalho.entity.Contact;
import com.mvalho.entity.Person;
import com.mvalho.repository.PersonRepository;

import javassist.NotFoundException;

@Controller
@RequestMapping("/person")
public class PersonController {
	private static final String PERSON_ADD = "person/add";
	private static final String REDIRECT_PERSON = "redirect:/person";
	private static final String SUCCESS_MSG = "Person was succefully deleted.";
	private static final String SUCCESS_KEY = "success";
	private static final String NOT_FOUND_MSG = "Could not find Person with ID: ";
	private static final String PERSON = "person";
	private static final String DELETE_PERSON_ID = "/delete/{personId}";
	private static final String EDIT_PERSON_ID = "/edit/{personId}";
	private static final String ADD = "/add";
	private static final String PERSON_INDEX = "person/index";
	private static final String INDEX = "/";
	private static final String LIST_OF_PERSONS = "listOfPersons";
	
	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping(value = INDEX, method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.addObject(LIST_OF_PERSONS, this.personRepository.findAll());
		mav.setViewName(PERSON_INDEX);
		
		return mav;
	}
	
	@RequestMapping(value = ADD, method = RequestMethod.GET)
	public ModelAndView addEdit(ModelAndView mav) {
		Person person = new Person();
		person.setContacts(new ArrayList<Contact>());
		
		if(mav.getModelMap().containsKey(PERSON)) {
			person = (Person) mav.getModelMap().get(PERSON);
		}
		
		mav.addObject(PERSON, person);
		mav.setViewName(PERSON_ADD);
		return mav;
	}
	
	@RequestMapping(value = ADD, method = RequestMethod.POST)
	public ModelAndView save(Person person, ModelAndView mav) {
		addPersonToContact(person);
		this.personRepository.save(person);
		mav.setViewName(REDIRECT_PERSON + INDEX);
		return mav;
	}
	
	private void addPersonToContact(Person person) {
		person.getContacts().stream().forEach(c -> c.setPerson(person));
	}

	@RequestMapping(value = EDIT_PERSON_ID, method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("personId") Long id, ModelAndView mav) throws NotFoundException {
		if(this.personRepository.exists(id)) {
			mav.addObject(PERSON, this.personRepository.findOne(id));
			return addEdit(mav);
		} else {
			throw new NotFoundException(NOT_FOUND_MSG + id);
		}
	}
	
	@RequestMapping(value = DELETE_PERSON_ID, method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("personId") Long id, ModelAndView mav) throws NotFoundException {
		if(this.personRepository.exists(id)) {
			this.personRepository.delete(id);
			mav.addObject(SUCCESS_KEY, SUCCESS_MSG);
			mav.setViewName(REDIRECT_PERSON + INDEX);
			
			return mav;
		} else {
			throw new NotFoundException(NOT_FOUND_MSG + id);
		}
	}
}
