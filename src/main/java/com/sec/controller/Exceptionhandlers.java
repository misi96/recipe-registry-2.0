package com.sec.controller;




import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.google.gson.JsonObject;

@RestControllerAdvice
public class Exceptionhandlers {
	
	String CreateJsonFromExceptionAndRequest(Exception ex, WebRequest request) {
		JsonObject Json =new JsonObject();
		Json.addProperty("type", ex.getClass().getSimpleName());
		Json.addProperty("message", ex.getMessage());
		Json.addProperty("path", request.getContextPath());
		Json.addProperty("description", request.getDescription(true));
		Json.addProperty("user", request.getRemoteUser());
		return Json.toString();
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> exception(Exception ex, WebRequest request){
			
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		 	
		return new ResponseEntity<String>(CreateJsonFromExceptionAndRequest(ex,request),headers,HttpStatus.NOT_FOUND);
		
	}
	
	

}
