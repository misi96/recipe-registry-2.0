package com.sec.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sec.entity.AttachedRecipePicture;

public interface AttachedPictureRepository extends  CrudRepository<AttachedRecipePicture, Long> {

	@Query("SELECT pic.pictureID FROM AttachedRecipePicture pic WHERE pic.recipe.postableID = :postableID")
	long[] GetAttachedPicIDs(@Param("postableID")long postableID);
	
	
}
