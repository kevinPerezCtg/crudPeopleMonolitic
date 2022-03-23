package com.pragma.crudpeople.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "persons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "firtsName")
	private String firtsName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "identification")
	private String identification;

	@Column(name = "dateOfBirth")
	private LocalDate dateOfBirth;

	@Column(name = "cityOfBirth")
	private String cityOfBirth;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "image_id", referencedColumnName = "id")
	private Image image;

}
