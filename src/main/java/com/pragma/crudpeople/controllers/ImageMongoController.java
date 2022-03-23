package com.pragma.crudpeople.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pragma.crudpeople.jsons.ImageMongoJson;
import com.pragma.crudpeople.services.IImageMongoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/image-mongo")
@RequiredArgsConstructor
public class ImageMongoController {
	
	private final IImageMongoService imageMongoService;
	
	@GetMapping("")
	public ResponseEntity<List<ImageMongoJson>> getMongoImages(){
		return new ResponseEntity<List<ImageMongoJson>>(imageMongoService.getMongoImages(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ImageMongoJson> getMongoImageId(@PathVariable(value = "id") String id){
		return new ResponseEntity<ImageMongoJson>(imageMongoService.getMongoImageById(id), HttpStatus.OK);
	}
	
	@PostMapping("")
	public void createImageMongo(MultipartFile image) throws Exception {
		imageMongoService.createImageMongo(image);
	}

	@PutMapping("")
	public void updatePerson(MultipartFile image, @RequestParam("id") String id) throws Exception {
		imageMongoService.updateImageMongo(id,image);
	}

	@DeleteMapping("{id}")
	public void deletePerson(@PathVariable(value = "id") String id){
		imageMongoService.deleteImageMongo(id);
	}

}
