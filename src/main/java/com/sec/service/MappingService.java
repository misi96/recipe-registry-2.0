package com.sec.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import com.sec.DTO.CommentDTO;
import com.sec.DTO.PostDTO;
import com.sec.entity.Comment;
import com.sec.entity.Post;


public class MappingService {

	private ModelMapper mapper = null;
	
	
	public MappingService() {
		mapper = new ModelMapper();
		mapper.addMappings(new PropertyMap<CommentDTO, Comment>() {
			
			@Override
			protected void configure() {
				
				skip().setCreationDate(null);
				skip().setCreatedBy(null);
				
			}  });
		
		mapper.addMappings(new PropertyMap<PostDTO, Post>() {
			
			@Override
			protected void configure() {
				
				skip().setCreationDate(null);
				skip().setCreatedBy(null);
				
			}  });
		
		


	}
	
	public ModelMapper GetMapper() {
		
		return mapper;
		
	}
	
	
	public <T, S> T MapElements(S source, Class<T> destinationType) {
		
		
	    return mapper.map(source, destinationType);
	    
	} 
	
	public <T, S> Page<T> MapPages(Class<S> source, Class<T> destinationType,Page<S> page) {
		
		
		Class<T> type = destinationType;	
		Page<T> targetPage = page.map(new Converter<S, T> (){
			
			@Override
			public T convert(S source) {
				
				
				 return MapElements(source, type);
				 
			}
			
			
			
		});
		
		return targetPage;
		   
	

}}
