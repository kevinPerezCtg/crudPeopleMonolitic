package com.pragma.crudpeople.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.pragma.crudpeople.jsons.ImageMongoJson;

public interface IImageMongoService {

	public List<ImageMongoJson> getMongoImages();
	public ImageMongoJson getMongoImageById(String id);
	public String createImageMongo(MultipartFile image);
	public String updateImageMongo(String id,MultipartFile image);
	public String deleteImageMongo(String id);
	
}
