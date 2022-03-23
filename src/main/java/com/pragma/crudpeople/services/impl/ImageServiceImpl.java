package com.pragma.crudpeople.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.pragma.crudpeople.entities.Image;
import com.pragma.crudpeople.exceptions.NoDataFoundException;
import com.pragma.crudpeople.exceptions.ResourceNotFoundException;
import com.pragma.crudpeople.jsons.ImageJson;
import com.pragma.crudpeople.repositories.IImageRepository;
import com.pragma.crudpeople.services.IImageService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {
	
	private final IImageRepository imageRepository;
	
	public static final ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<ImageJson> getImages(){
		List<Image> imagesEntity = imageRepository.findAll();
		if (imagesEntity.size() > 0) {
			return imagesEntity.stream().map(service -> modelMapper.map(service, ImageJson.class))
					.collect(Collectors.toList());
		}		
		throw new NoDataFoundException("There are not images");
	}

	@Override
	public ImageJson getImageById(Long id){		
		return modelMapper.map(getImageEntity(id), ImageJson.class);
	}
	
	private Image getImageEntity(Long id){
		return imageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image was not found"));
	}	

}
