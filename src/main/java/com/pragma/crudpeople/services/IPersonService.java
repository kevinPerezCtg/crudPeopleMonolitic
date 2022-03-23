package com.pragma.crudpeople.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pragma.crudpeople.jsons.CreatePersonJson;
import com.pragma.crudpeople.jsons.PersonJson;

public interface IPersonService {
	
	public List<PersonJson> getPersons();
	public PersonJson getPersonById(Long id);
	public String createPerson(CreatePersonJson createPersonJson, MultipartFile image); 
	public String updatePerson(CreatePersonJson createPersonJson, MultipartFile image); 
	public String deletePerson(Long id);

}
