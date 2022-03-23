package com.pragma.crudpeople.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pragma.crudpeople.jsons.CreatePersonJson;
import com.pragma.crudpeople.jsons.PersonJson;
import com.pragma.crudpeople.services.IPersonService;

@RestController
@RequestMapping(path = "/person")
public class PersonController {

	@Autowired
	IPersonService personService;

	@GetMapping("")
	public ResponseEntity<List<PersonJson>> getPersons(){
		return new ResponseEntity<List<PersonJson>>(personService.getPersons(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<PersonJson> getPersonById(@PathVariable(value = "id") Long id){
		return new ResponseEntity<PersonJson>(personService.getPersonById(id), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<String> createPerson(MultipartFile image, @RequestParam("firtsName") String firtsName,
			@RequestParam("lastName") String lastName, @RequestParam("identification") String identification,
			@RequestParam("dateOfBirth") String dateOfBirth, @RequestParam("cityOfBirth") String cityOfBirth)
			throws Exception {
		return new ResponseEntity<String>(personService.createPerson(new CreatePersonJson(null, firtsName, lastName, identification,
				LocalDate.parse(dateOfBirth), cityOfBirth), image), HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<String> updatePerson(MultipartFile image, @RequestParam("id") String id,
			@RequestParam("firtsName") String firtsName, @RequestParam("lastName") String lastName,
			@RequestParam("identification") String identification, @RequestParam("dateOfBirth") String dateOfBirth,
			@RequestParam("cityOfBirth") String cityOfBirth) throws Exception {
		return new ResponseEntity<String>(personService.updatePerson(new CreatePersonJson(Long.parseLong(id), firtsName, lastName, identification,
				LocalDate.parse(dateOfBirth), cityOfBirth), image), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deletePerson(@PathVariable(value = "id") Long id){
		return new ResponseEntity<String>(personService.deletePerson(id), HttpStatus.OK);
	}

}
