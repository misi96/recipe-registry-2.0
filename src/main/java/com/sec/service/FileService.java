package com.sec.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.sec.entity.AttachedRecipePicture;
import com.sec.entity.Recipe;
import com.sec.entity.User;
import com.sec.repo.AttachedPictureRepository;
import com.sec.repo.RecipeRepository;

@Service
public class FileService {

	
	@Autowired
	RecipeRepository recipeRepository;
	

	@Autowired
	AttachedPictureRepository attachedPictureRepository;
	
	final String upLoadDir = "src" + File.separator + "main" + File.separator +  "resources" + File.separator + "static" + File.separator + "MainPicturesOfRecipes" + File.separator;
	@Value("${recipes.pictures.fileNames.Prefix}")
	String fileNamePrefix;
	
	@Value("${recipes.pictures.fileNames.Suffix}")
	String fileNameSuffix;
	
	@Transactional
	public boolean AddMainPictureToRecipe(MultipartFile file, long postableID,User user) throws IOException {
		
		//if(user.getId()==recipeRepository.getCreatedById(postableID)) {
		
		ByteArrayInputStream bais = new ByteArrayInputStream(file.getBytes());
		BufferedImage bi = ImageIO.read(bais);
		String fileName = fileNamePrefix + postableID + fileNameSuffix;
		File outputfile = new File(upLoadDir + fileName);
		
	    ImageIO.write(bi, "jpg", outputfile);
		boolean exists = outputfile.exists();
		if(exists) {
		recipeRepository.SetMainPicFlag(true, postableID);	
		}
		
		return exists;
		
		}
		/*else {
			
			return false;
		}
		
	}*/
	public ResponseEntity<byte[]> GetMainPictureOfRecipe(long postableID) {
		
		String fileName = fileNamePrefix + postableID + fileNameSuffix;
		byte[] fileContent=null;
		try {
			fileContent = Files.readAllBytes(new File(upLoadDir + fileName).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(fileContent);
	}
	
	
	public ResponseEntity<byte[]> GetAttachedPictureOfRecipe(long postableID,long pictureID) {
		AttachedRecipePicture picture = attachedPictureRepository.findOne(pictureID);
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(picture.getPicture());
		
	}
	
	public boolean AddAttachedPictureToRecipe(MultipartFile file, long postableID) throws IOException {
		Recipe recipe =recipeRepository.findOne(postableID);
		
		AttachedRecipePicture attachedRecipePicture = new AttachedRecipePicture(file.getBytes(),recipe);
		
		attachedPictureRepository.save(attachedRecipePicture);
		
		
		return true;
		
	}
	public long[] GetAttachedPicIDs(long postableID) {
		
		
		
		return attachedPictureRepository.GetAttachedPicIDs(postableID);
		
	}
	
	
}
