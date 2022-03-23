package com.pragma.crudpeople.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pragma.crudpeople.entities.ImageMongo;
import com.pragma.crudpeople.exceptions.NoDataFoundException;
import com.pragma.crudpeople.exceptions.ResourceNotFoundException;
import com.pragma.crudpeople.jsons.ImageMongoJson;
import com.pragma.crudpeople.repositories.IImageMongoRepository;
import com.pragma.crudpeople.services.IImageMongoService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageMongoServiceImpl implements IImageMongoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageMongoServiceImpl.class);

	private final IImageMongoRepository imageMongoRepository;

	public static final ModelMapper modelMapper = new ModelMapper();

	public List<ImageMongoJson> getMongoImages() {
		List<ImageMongo> imagesMongoEntity = imageMongoRepository.findAll();
		if (!imagesMongoEntity.isEmpty()) {
			return imagesMongoEntity.stream().map(service -> modelMapper.map(service, ImageMongoJson.class))
					.collect(Collectors.toList());
		}
		throw new NoDataFoundException("There are not images");
	}

	public ImageMongoJson getMongoImageById(String id) {
		return modelMapper.map(getImageMongoEntity(id), ImageMongoJson.class);
	}

	public String createImageMongo(MultipartFile image) {
		String imageInBase64;
		try {
			imageInBase64 = convertImageToBase64(image);
			ImageMongo imageMongoToSave = new ImageMongo();
			imageMongoToSave.setUrlImage(imageInBase64);
			imageMongoRepository.save(imageMongoToSave);
		} catch (IOException e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return "Image created succesfully";
	}

	public String updateImageMongo(String id, MultipartFile image) {
		String imageInBase64;
		try {
			imageInBase64 = convertImageToBase64(image);
			ImageMongo imageMongoToSave = imageMongoRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Image was not found"));			
			imageMongoToSave.setUrlImage(imageInBase64);
			imageMongoRepository.save(imageMongoToSave);
		} catch (IOException e) {
			LOGGER.error("INTERNAL_SERVER_ERROR", e.getMessage());
		}
		return "Image updated succesfully";
	}
	
	@Override
	public String deleteImageMongo(String id) {
		ImageMongo imageMongo = imageMongoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Image was not found"));
		imageMongoRepository.deleteById(imageMongo.getId());
		return "Image deleted succesfully";
	}

	public String convertImageToBase64(MultipartFile image) throws IOException {
		byte[] bytes = image.getBytes();
		return java.util.Base64.getEncoder().encodeToString(bytes);
	}

	private ImageMongo getImageMongoEntity(String id) {
		return imageMongoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Image was not found"));
	}	

}
