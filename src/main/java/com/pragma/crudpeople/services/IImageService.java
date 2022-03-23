package com.pragma.crudpeople.services;

import java.util.List;

import com.pragma.crudpeople.jsons.ImageJson;

public interface IImageService {
	
	public List<ImageJson> getImages();
	public ImageJson getImageById(Long id);

}
