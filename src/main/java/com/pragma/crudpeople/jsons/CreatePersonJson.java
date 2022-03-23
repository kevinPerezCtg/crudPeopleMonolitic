package com.pragma.crudpeople.jsons;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePersonJson {

	@JsonProperty("id")
	private Long id;
		
	@JsonProperty("firtsName")
	private String firtsName;
	
	@JsonProperty("lastName")
	private String lastName;
	
	@JsonProperty("identification")
	private String identification;
	
	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth;
	
	@JsonProperty("cityOfBirth")
	private String cityOfBirth;	
	
}
