package com.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import com.sec.SecApplication;
import com.sec.entity.User;
import com.sec.service.FileService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecApplication.class)
@AutoConfigureMockMvc
public class FileControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private FileService fileService;
	
	MockMultipartFile mockFile;
	@Before
	public void testInit() {
		mockFile = new MockMultipartFile("files", "filename.txt", "text/plain", "hello".getBytes(StandardCharsets.UTF_8));
	}
	
	
	//TODO
	/*@Test
	@WithMockUser()
	public void testAddMainPictureToRecipe() throws Exception {
		
		Integer postableID = 10;
		String URI = "/recipes/" + postableID.toString() + "/pictures/main";
		Boolean output = true;
		String inputInJson = Skeleton.mapToJson(mockFile);
		String outputInJson = Skeleton.mapToJson(output);
		
		Mockito.when(fileService.AddMainPictureToRecipe(Mockito.any(MultipartFile.class), Mockito.anyLong(), Mockito.any(User.class))).thenReturn(output);
		
		this.mockMvc
				.perform(post(URI)
				
				//.content(outputInJson)
				.accept(MediaType.APPLICATION_JSON))
				
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(outputInJson));
	}*/
	
}
