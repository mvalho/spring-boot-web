package com.mvalho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvalho.entity.Person;
import com.mvalho.repository.PersonRepository;

import javassist.NotFoundException;

@Controller
@RequestMapping("/person")
public class PersonController {
	@Autowired
	private PersonRepository personRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView mav) {
		mav.addObject("listOfPersons", this.personRepository.findAll());
		mav.setViewName("person/index");
		
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(ModelAndView mav) {
		Person person = new Person();
		
		if(mav.getModelMap().containsKey("person")) {
			person = (Person) mav.getModelMap().get("person");
		}
		
		mav.addObject("person", person);
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView save(Person person, ModelAndView mav) {
		this.personRepository.save(person);
		
		return mav;
	}
	
	@RequestMapping(value = "/edit/{personId}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable("personId") Long id, ModelAndView mav) throws NotFoundException {
		if(this.personRepository.exists(id)) {
			mav.addObject("person", this.personRepository.findOne(id));
			return add(mav);
		} else {
			throw new NotFoundException("Could not find Person with ID: " + id);
		}
	}
	
	@RequestMapping(value = "/delete/{personId}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("personId") Long id, ModelAndView mav) throws NotFoundException {
		if(this.personRepository.exists(id)) {
			this.personRepository.delete(id);
			mav.addObject("success", "Person was succefully deleted.");
			mav.setViewName("person/index");
			
			return mav;
		} else {
			throw new NotFoundException("Could not find Person with ID: " + id);
		}
	}
}
