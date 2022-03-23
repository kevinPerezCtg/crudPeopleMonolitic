package com.pragma.crudpeople.controllers;

import java.io.IOException;
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

import com.pragma.crudpeople.jsons.ImageMongoJson;
import com.pragma.crudpeople.services.IImageMongoService;

public class ImageMongoControllerTest {

	private static final long IMAGE_ID = 1l;
	private static final String URL_IMAGE = "abcd";
	
	private static final String OK_STATUS = "200 OK";
	public static final ImageMongoJson IMAGE_JSON = new ImageMongoJson();
	
	public static final List<ImageMongoJson> LIST_IMAGES_JSON = new ArrayList<>();
	
	@Mock
	IImageMongoService imageMongoService;
	
	@InjectMocks
	ImageMongoController imageMongoController;
	
	@BeforeEach
	public void init() throws IOException {
		MockitoAnnotations.openMocks(this);
		IMAGE_JSON.setId(Long.toString(IMAGE_ID));
		IMAGE_JSON.setUrlImage(URL_IMAGE);
		LIST_IMAGES_JSON.add(IMAGE_JSON);

		Mockito.when(imageMongoService.getMongoImageById(Long.toString(IMAGE_ID))).thenReturn(IMAGE_JSON);
		Mockito.when(imageMongoService.getMongoImages()).thenReturn(LIST_IMAGES_JSON);
	}
	
	@Test
	public void getImagesTest() {
		final ResponseEntity<List<ImageMongoJson>> listImages = imageMongoController.getMongoImages();
		Assertions.assertEquals(listImages.getStatusCode().toString(), OK_STATUS);
	}

	@Test
	public void getImageByIdTest() {
		final ResponseEntity<ImageMongoJson> image = imageMongoController.getMongoImageId(Long.toString(IMAGE_ID));
		Assertions.assertEquals(image.getStatusCode().toString(), OK_STATUS);
	}
	
}
