package com.pragma.crudpeople.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pragma.crudpeople.entities.Image;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {

}
