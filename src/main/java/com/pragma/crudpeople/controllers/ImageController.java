package com.pragma.crudpeople.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pragma.crudpeople.jsons.ImageJson;
import com.pragma.crudpeople.services.IImageService;

@RestController
@RequestMapping("/image")
public class ImageController {
	
	@Autowired
	IImageService imageService;

	@GetMapping("")
	public ResponseEntity<List<ImageJson>> getImages() {
		return new ResponseEntity<List<ImageJson>>(imageService.getImages(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<ImageJson> getImageById(@PathVariable(value = "id") Long id) {
		return new ResponseEntity<ImageJson>(imageService.getImageById(id), HttpStatus.OK);
	}

}
