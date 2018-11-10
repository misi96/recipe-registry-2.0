package com.sec.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/posts/{PostID}/pictures")
public class FileController {
	
	 @PostMapping
	    public boolean PicAttach(@RequestParam("file") MultipartFile file,@PathVariable int PostID) {
		 
		 
			return false;
  
	    }
	 @GetMapping("/{picIndex}")
	    public ResponseEntity<byte[]> PicDownlaod(@PathVariable("PostID") int PostID,@PathVariable("picIndex") int picIndex) {
	    	
	    	byte[] image = null;
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);

	    }	 
	 

}
