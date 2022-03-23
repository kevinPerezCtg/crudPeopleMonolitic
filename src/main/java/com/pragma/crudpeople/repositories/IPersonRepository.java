package com.pragma.crudpeople.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pragma.crudpeople.entities.Person;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {

}
