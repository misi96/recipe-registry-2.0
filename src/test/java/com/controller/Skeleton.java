package com.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Skeleton {
	
	public static String mapToJson(Object object) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
