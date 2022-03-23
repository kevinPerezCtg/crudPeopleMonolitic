package com.pragma.crudpeople.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.pragma.crudpeople.jsons.CreatePersonJson;
import com.pragma.crudpeople.jsons.ImageJson;
import com.pragma.crudpeople.jsons.PersonJson;
import com.pragma.crudpeople.services.IPersonService;

public class PersonControllerTest {

	private static final long PERSON_ID = 1l;
	private static final String FIRTS_NAME = "Kevin";
	private static final String LAST_NAME = "Kevin";
	private static final int IDENTIFICATION = 12345;
	private static final LocalDate DATE_OF_BIRTH = LocalDate.parse("1995-03-29");
	private static final String CITY_OF_BIRTH = "Cartagena";

	private static final String OK_STATUS = "200 OK";
	public static final PersonJson PERSON_JSON = new PersonJson();

	public static final CreatePersonJson CREATE_PERSON_JSON = new CreatePersonJson();

	public static final List<PersonJson> LIST_PERSONS_JSON = new ArrayList<>();

	public static final ImageJson IMAGE_JSON = new ImageJson();
	private static final long IMAGE_ID = 1l;
	private static final String IMAGE_URL = "abcd";

	@Mock
	IPersonService personService;

	@InjectMocks
	PersonController personController;

	@BeforeEach
	public void init() throws IOException {
		MockitoAnnotations.openMocks(this);
		PERSON_JSON.setFirtsName(FIRTS_NAME);
		PERSON_JSON.setLastName(LAST_NAME);
		PERSON_JSON.setIdentification(IDENTIFICATION);
		PERSON_JSON.setDateOfBirth(DATE_OF_BIRTH);
		PERSON_JSON.setCityOfBirth(CITY_OF_BIRTH);
		IMAGE_JSON.setId(IMAGE_ID);
		IMAGE_JSON.setUrlImage(IMAGE_URL);
		PERSON_JSON.setImage(IMAGE_JSON);

		LIST_PERSONS_JSON.add(PERSON_JSON);

		CREATE_PERSON_JSON.setId(PERSON_ID);
		CREATE_PERSON_JSON.setFirtsName(FIRTS_NAME);
		CREATE_PERSON_JSON.setLastName(LAST_NAME);
		CREATE_PERSON_JSON.setIdentification(Integer.toString(IDENTIFICATION));
		CREATE_PERSON_JSON.setDateOfBirth(DATE_OF_BIRTH);
		CREATE_PERSON_JSON.setCityOfBirth(CITY_OF_BIRTH);

		Mockito.when(personService.getPersonById(PERSON_ID)).thenReturn(PERSON_JSON);
		Mockito.when(personService.getPersons()).thenReturn(LIST_PERSONS_JSON);
		Mockito.when(personService.deletePerson(PERSON_ID)).thenReturn("Person Was deleted succesfully");
	}

	@Test
	public void getPersonsTest() {
		final ResponseEntity<List<PersonJson>> listPersons = personController.getPersons();
		Assertions.assertEquals(listPersons.getStatusCode().toString(), OK_STATUS);
		Assertions.assertEquals(listPersons.getBody(), LIST_PERSONS_JSON);
	}

	@Test
	public void getPersonByIdTest() {
		final ResponseEntity<PersonJson> person = personController.getPersonById(PERSON_ID);
		Assertions.assertEquals(person.getStatusCode().toString(), OK_STATUS);
		Assertions.assertEquals(person.getBody(), PERSON_JSON);
	}
	
	@Test
	public void deletePersonTest() throws Exception {		
		final ResponseEntity<String> response = personController.deletePerson(CREATE_PERSON_JSON.getId());
		Assertions.assertEquals(response.getStatusCode().toString(), OK_STATUS);
	}

}
