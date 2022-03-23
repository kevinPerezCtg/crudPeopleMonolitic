package com.pragma.crudpeople.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.pragma.crudpeople.entities.Image;
import com.pragma.crudpeople.entities.Person;
import com.pragma.crudpeople.exceptions.NoDataFoundException;
import com.pragma.crudpeople.exceptions.ResourceNotFoundException;
import com.pragma.crudpeople.repositories.IPersonRepository;
import com.pragma.crudpeople.services.impl.PersonServiceImpl;

public class PersonServiceTest {

	private static final long PERSON_ID = 1l;
	private static final String FIRTS_NAME = "Kevin";
	private static final String LAST_NAME = "Kevin";
	private static final String IDENTIFICATION = "12345";
	private static final LocalDate DATE_OF_BIRTH = LocalDate.parse("1995-03-29");
	private static final String CITY_OF_BIRTH = "Cartagena";

	public static final Person PERSON = new Person();

	public static final List<Person> LIST_PERSONS = new ArrayList<>();

	public static final Image IMAGE = new Image();
	private static final long IMAGE_ID = 1l;
	private static final String IMAGE_URL = "abcd";

	@Mock
	IPersonRepository personRepository;

	@InjectMocks
	PersonServiceImpl personServiceImpl;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		PERSON.setFirtsName(FIRTS_NAME);
		PERSON.setLastName(LAST_NAME);
		PERSON.setIdentification(IDENTIFICATION);
		PERSON.setDateOfBirth(DATE_OF_BIRTH);
		PERSON.setCityOfBirth(CITY_OF_BIRTH);
		IMAGE.setId(IMAGE_ID);
		IMAGE.setUrlImage(IMAGE_URL);
		PERSON.setImage(IMAGE);

		LIST_PERSONS.add(PERSON);

	}

	@Test
	public void getPersonsTest() {
		Mockito.when(personRepository.findAll()).thenReturn(LIST_PERSONS);
		personServiceImpl.getPersons();
	}

	@Test
	public void getPersonsTestError() {
		Mockito.when(personRepository.findAll()).thenReturn(new ArrayList<>());
		assertThatThrownBy(() -> personServiceImpl.getPersons()).isInstanceOf(NoDataFoundException.class)
				.hasMessageContaining("There are not persons");
	}

	@Test
	public void getPersonByIdTest() {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(Optional.of(PERSON));
		personServiceImpl.getPersonById(PERSON_ID);
	}

	@Test
	public void getPersonByIdTestError() {
		Mockito.when(personRepository.findById(PERSON_ID)).thenReturn(Optional.empty());
		assertThatThrownBy(() -> personServiceImpl.getPersonById(PERSON_ID))
				.isInstanceOf(ResourceNotFoundException.class).hasMessageContaining("Person was not found");
	}

}
