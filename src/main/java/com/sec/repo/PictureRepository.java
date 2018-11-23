package com.sec.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import com.sec.entity.Picture;

public interface PictureRepository  extends CrudRepository<Picture, Long>{

	
	@Modifying
	int deleteByPictureID(long pictureID);
	
	
	
}
