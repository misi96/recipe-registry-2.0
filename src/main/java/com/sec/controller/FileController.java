package com.sec.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sec.entity.User;
import com.sec.service.FileService;

@RestController
@RequestMapping("/recipes/{postableID}/pictures")
public class FileController {
	@Autowired
	FileService fileService;
	
	  @Autowired
	  HttpServletRequest request;
	
	
	 @PostMapping("/main")
	    public boolean AddMainPictureToRecipe(@RequestParam("picture") MultipartFile file,@PathVariable int postableID,@AuthenticationPrincipal User user) throws IOException {
		 
		 
			return fileService.AddMainPictureToRecipe(file,postableID,user);
			
			
	    }
	 
	 
	 
	    
	    @GetMapping("/main")
	    public ResponseEntity<byte[]> GetMainPictureOfRecipe(@PathVariable("postableID") long postableID) {
	    	
	    	
	    	
			return fileService.GetMainPictureOfRecipe(postableID);

	    }
	    
	    @PostMapping("/attached")
	    public boolean AddAttachedPictureToRecipe(@RequestParam("picture") MultipartFile file,@PathVariable long postableID) throws IOException {
		 
		 
			return fileService.AddAttachedPictureToRecipe(file,postableID);

	    }
	    
	    @GetMapping("/attached/{pictureID}")
	    public ResponseEntity<byte[]> PicDownlaod(@PathVariable("postableID") long postableID,@PathVariable("pictureID") long pictureID) {
	    	
	    	
			return fileService.GetAttachedPictureOfRecipe(postableID, pictureID);

	    }
	    
	    @GetMapping("/attached/IDs")
	    public long[] GetAttachedPicIDs(@PathVariable("postableID") long postableID) {
	    	
	    	
			return fileService.GetAttachedPicIDs(postableID);

	    }
	    
	    

}
