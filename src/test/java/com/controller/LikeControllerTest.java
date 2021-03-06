package com.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.sec.SecApplication;
import com.sec.DTO.LikeDTO;
import com.sec.DTO.RoleDTO;
import com.sec.DTO.UserDTO;
import com.sec.entity.User;
import com.sec.service.LikeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecApplication.class)
@AutoConfigureMockMvc
public class LikeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private LikeService likeService;
	
	private UserDTO mockUserDTO = new UserDTO();
	private RoleDTO mockRoleDTO = new RoleDTO();
	private LikeDTO mockLikeDTO = new LikeDTO();
	private LikeDTO mockLikeDTO2 = new LikeDTO();
	private Set<RoleDTO> roleSet = new HashSet<RoleDTO>();
	private Page<LikeDTO> page;
	
	@Before
	public void testInit() throws Exception {	
		
		mockRoleDTO.setId(100000l);
		mockRoleDTO.setRole("USER");
		
		roleSet.add(mockRoleDTO);
		
		mockUserDTO.setUserName("Levii");
		mockUserDTO.setId(10l);
		mockUserDTO.setRoles(roleSet);
		
		mockLikeDTO.setCreatedBy(mockUserDTO);
		mockLikeDTO.setCreationDate(new Date());
		mockLikeDTO.setId(2l);
		
		mockLikeDTO2.setCreatedBy(mockUserDTO);
		mockLikeDTO2.setCreationDate(new Date());
		mockLikeDTO2.setId(5l);
		
		List<LikeDTO> likeDTOs = new ArrayList<>();
		likeDTOs.add(mockLikeDTO);
		likeDTOs.add(mockLikeDTO2);
		page = new PageImpl<>(likeDTOs);

	}
	
	
	@Test
	@WithMockUser
	public void testLikePost() throws Exception {
		Integer postID = 666;
		
		String URI = "/posts/" + postID.toString() + "/like";
		String outputInJson = Skeleton.mapToJson(mockLikeDTO);
		
		Mockito.when(likeService.LikePost(Mockito.anyInt())).thenReturn(mockLikeDTO);
				
		
		this.mockMvc
			.perform(post(URI))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json(outputInJson));
	}
	
	@Test
	@WithMockUser
	public void testGetLikes() throws Exception {
		Integer postID = 666;
		
		String URI = "/posts/" + postID.toString() + "/like";
		String outputInJson = Skeleton.mapToJson(page);
		
		Mockito.when(likeService.GetLikes(Mockito.anyInt(), Mockito.any(Pageable.class))).thenReturn(page);
				
		
		this.mockMvc
			.perform(get(URI))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().json(outputInJson));
	}
	
	@Test
	@WithMockUser
	public void testDeleteLike() throws Exception {
		Integer postID = 666;
		
		String URI = "/posts/" + postID.toString() + "/like";
		String outputInJson = Skeleton.mapToJson(1);
		
		Mockito.when(likeService.DeleteLike(Mockito.anyInt(), Mockito.any(User.class))).thenReturn(1);
		
		this.mockMvc
			.perform(delete(URI))
			.andExpect(status().isOk())
			.andExpect(content().json(outputInJson));
	}
}
