package com.pragma.crudpeople.jsons;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageJson {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("urlImage")
	private String urlImage;

}
