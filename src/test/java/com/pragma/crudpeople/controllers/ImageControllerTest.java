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

import com.pragma.crudpeople.jsons.ImageJson;
import com.pragma.crudpeople.services.IImageService;

public class ImageControllerTest {
	
	private static final long IMAGE_ID = 1l;
	private static final String URL_IMAGE = "abcd";
	
	private static final String OK_STATUS = "200 OK";
	public static final ImageJson IMAGE_JSON = new ImageJson();
	
	public static final List<ImageJson> LIST_IMAGES_JSON = new ArrayList<>();
	
	@Mock
	IImageService imageService;
	
	@InjectMocks
	ImageController imageController;
	
	@BeforeEach
	public void init() throws IOException {
		MockitoAnnotations.openMocks(this);
		IMAGE_JSON.setId(IMAGE_ID);
		IMAGE_JSON.setUrlImage(URL_IMAGE);
		LIST_IMAGES_JSON.add(IMAGE_JSON);

		Mockito.when(imageService.getImageById(IMAGE_ID)).thenReturn(IMAGE_JSON);
		Mockito.when(imageService.getImages()).thenReturn(LIST_IMAGES_JSON);
	}
	
	@Test
	public void getImagesTest() {
		final ResponseEntity<List<ImageJson>> listImages = imageController.getImages();
		Assertions.assertEquals(listImages.getStatusCode().toString(), OK_STATUS);
	}

	@Test
	public void getImageByIdTest() {
		final ResponseEntity<ImageJson> image = imageController.getImageById(IMAGE_ID);
		Assertions.assertEquals(image.getStatusCode().toString(), OK_STATUS);
	}

}
