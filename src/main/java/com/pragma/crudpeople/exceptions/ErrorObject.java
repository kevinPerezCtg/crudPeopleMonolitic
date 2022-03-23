package com.pragma.crudpeople.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorObject {

	private int statusCode;	
	private String message;
	
}
