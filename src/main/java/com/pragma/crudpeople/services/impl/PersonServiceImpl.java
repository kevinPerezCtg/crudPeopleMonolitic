package com.pragma.crudpeople.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pragma.crudpeople.entities.Image;
import com.pragma.crudpeople.entities.Person;
import com.pragma.crudpeople.exceptions.BadRequestException;
import com.pragma.crudpeople.exceptions.NoDataFoundException;
import com.pragma.crudpeople.exceptions.ResourceNotFoundException;
import com.pragma.crudpeople.jsons.CreatePersonJson;
import com.pragma.crudpeople.jsons.PersonJson;
import com.pragma.crudpeople.repositories.IPersonRepository;
import com.pragma.crudpeople.services.IPersonService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements IPersonService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);    

	private final IPersonRepository personRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public List<PersonJson> getPersons() {
		List<Person> personsEntity = personRepository.findAll();
		if(!personsEntity.isEmpty()) {
			return personsEntity.stream().map(service -> modelMapper.map(service, PersonJson.class))
					.collect(Collectors.toList());
		}		
		throw new NoDataFoundException("There are not persons");
	}

	public PersonJson getPersonById(Long id){
		return modelMapper.map(getPersonEntity(id), PersonJson.class);
	}

	private Person getPersonEntity(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person was not found"));
	}

	@Transactional
	public String createPerson(CreatePersonJson createPersonJson, MultipartFile image) throws BadRequestException {		
		try {
			String imageInBase64 = convertImageToBase64(image);			
			Image imageToSave = setImageData(imageInBase64);
			Person person = setPersonData(createPersonJson);
			person.setImage(imageToSave);
			personRepository.save(person);			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			LOGGER.error("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return "Person Was craeted succesfully";
	}
	
	public String updatePerson(CreatePersonJson createPersonJson, MultipartFile image) throws BadRequestException {
		try {
			String imageInBase64 = convertImageToBase64(image);			
			Image imageToSave = setImageData(imageInBase64);
			Person personToUpdate = setPersonData(createPersonJson);
			personToUpdate.setImage(imageToSave);
			personRepository.save(personToUpdate);			
		} catch (Exception e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return "Person Was updated succesfully";		
	}
	
	public String deletePerson(Long id){
		Person person = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Person was not found"));
		personRepository.deleteById(person.getId());
		return "Person Was deleted succesfully";
	}
	
	public Image setImageData(String imageInBase64 ) {
		final Image imag = new Image();
		imag.setUrlImage(imageInBase64);		
		return imag;
	}
	
	public Person setPersonData(CreatePersonJson createPersonJson) {
		final Person person = new Person();
		if(createPersonJson.getId() != null) {
			person.setId(createPersonJson.getId());
		}
		person.setFirtsName(createPersonJson.getFirtsName());
		person.setLastName(createPersonJson.getLastName());
		person.setIdentification(createPersonJson.getIdentification());
		person.setDateOfBirth(createPersonJson.getDateOfBirth());
		person.setCityOfBirth(createPersonJson.getCityOfBirth());	
		return person;
	}
	
	public String convertImageToBase64(MultipartFile image) throws IOException {		
		byte[] bytes = image.getBytes();	 				
		return java.util.Base64.getEncoder().encodeToString(bytes);
	}

}
