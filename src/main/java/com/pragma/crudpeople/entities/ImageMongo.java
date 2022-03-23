package com.pragma.crudpeople.entities;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value = "Images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageMongo {
	
	@Id
	private String id;
	private String urlImage;

}
