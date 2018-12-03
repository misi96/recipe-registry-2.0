package com.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sec.SecApplication;
import com.sec.DTO.CommentDTO;
import com.sec.DTO.UserDTO;
import com.sec.entity.User;
import com.sec.service.CommentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecApplication.class)
@AutoConfigureMockMvc
public class CommentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CommentService commentService;
	
	
	private CommentDTO mockCommentDTO1 = new CommentDTO();
	private CommentDTO mockCommentDTO2 = new CommentDTO();
	private User mockUser = new User();
	private Page<CommentDTO> page;
	//private String URI = "/posts/1/comment";
	private static final ObjectMapper mapper=new ObjectMapper();
	
	@Before
	public void testInit() throws Exception {
	
		
		mockCommentDTO1.setId(666);
		mockCommentDTO1.setContent("Very delicious meal!");
		mockCommentDTO1.setCreatedBy(new UserDTO());
		mockCommentDTO1.setCreationDate(new Date());
		
		mockCommentDTO2.setId(2);
		mockCommentDTO2.setContent("Tastes like sh*t");
		mockCommentDTO2.setCreatedBy(new UserDTO());
		mockCommentDTO2.setCreationDate(new Date());
		
		mockUser.setActivation("active");
		mockUser.setEmail("user@gmail.com");
		mockUser.setEnabled(true);
		mockUser.setId(1000101010l);
		mockUser.setPassword("password111");
		mockUser.setUserName("Levi");
		
		
		List<CommentDTO> commentDTOs = new ArrayList<>();
		commentDTOs.add(mockCommentDTO1);
		commentDTOs.add(mockCommentDTO2);
		page = new PageImpl<>(commentDTOs);
	}
	
	@Test
	@WithMockUser
	public void testCommentPost() throws Exception {

		Integer postID = 1;
		
		String URI = "/posts/" + postID.toString() + "/comments";
		
		Mockito.when(commentService.AddCommentToPost(Mockito.anyLong(), Mockito.any(CommentDTO.class))).thenReturn(mockCommentDTO1);
		
		Map<String,Object> input = new HashMap<>();
		//input.put("PostID", "1");
		input.put("CommentDTO", mockCommentDTO1);
		
		String inputInJson = this.mapToJson(input);
		String outputInJson = this.mapToJson(mockCommentDTO1);
		
		this.mockMvc
			.perform(post(URI)
			.contentType(MediaType.APPLICATION_JSON)
			.content(inputInJson)
			.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json(outputInJson));
		
		
		/*RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());*/
	}

	@Test
	@WithMockUser
	public void testGetCommentList() throws Exception {
		
		Integer postID = 10;
		String outputInJson = this.mapToJson(page);
		String URI = "/posts/" + postID + "/comments";
		
		Mockito.when(commentService.GetCommentList(Mockito.anyInt(), Mockito.any(Pageable.class))).thenReturn(page);
		
		this.mockMvc
				.perform(get(URI)
				//.param("PostID", "10")
				.param("page", "3")
				.param("size", "2")
				.contentType(MediaType.APPLICATION_JSON)
				//.content(outputInJson)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(outputInJson));
	}
	
	@Test
	@WithMockUser
	public void testDeleteComment() throws Exception {
		
		Integer commentID = 666;
		Integer postID = 1;
		Integer output = 1;
		String outputInJson = this.mapToJson(output);
		String URI = "/posts/" + postID.toString() + "/comments";
		
		Mockito.when(commentService.DeleteComment(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(User.class))).thenReturn(output);
		
		//Map<String,Object> input = new HashMap<>();
		//input.put("PostID", "1");
		//input.put("user", mockUser);
		//input.put("commentID", commentID.toString());
		this.mockMvc
				.perform(delete(URI + "/" + commentID.toString())
				//.content(mapper.writeValueAsString(input))
				.contentType(MediaType.APPLICATION_JSON)
				//.content(outputInJson)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(outputInJson));
	}
	
	
	
	
	
	private String mapToJson(Object object) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
