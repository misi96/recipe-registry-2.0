package com.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sec.SecApplication;
import com.sec.DTO.CircularEmail;
import com.sec.service.AdminService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SecApplication.class)
@AutoConfigureMockMvc
public class AdminControllerTest {


	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AdminService adminService;
	
	CircularEmail circularEmail;
	List<String> targets;
	
	@Before
	public void initTest() {
		targets = new ArrayList<>();
		targets.add("neki");
		targets.add("nekiis");
		targets.add("ide");
		
		String subject = "1$ recipe vs 1000$ recipe";
		String body = "sample text";
		
		circularEmail = new CircularEmail(targets, subject, body);
	}
	
	
	@Test
	@WithMockUser(username="user", authorities = { "USER" })
	public void testDeleteUserAsUser() throws Exception {
		
		Integer userID = 10;
		String URI = "/admin/users/" + userID.toString();
		
		this.mockMvc
				.perform(delete(URI))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username="admin", authorities = { "ADMIN", "USER" })
	public void testDeleteUserAsAdmin() throws Exception {
		
		Integer userID = 10;
		String URI = "/admin/users/" + userID.toString();
		Integer output = 1;
		String outputInJson = Skeleton.mapToJson(output);
		
		Mockito.when(adminService.deleteUser(Mockito.anyInt())).thenReturn(output);
		
		this.mockMvc
				.perform(delete(URI))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(outputInJson));
	}
	
	
	@Test
	@WithMockUser(username="user", authorities = { "USER" })
	public void testDeletePostAsUser() throws Exception {
		
		Integer postID = 10;
		String URI = "/admin/posts/" + postID.toString();
		
		this.mockMvc
				.perform(delete(URI))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username="admin", authorities = { "ADMIN", "USER" })
	public void testDeletePostAsAdmin() throws Exception {
		
		Integer postID = 10;
		String URI = "/admin/posts/" + postID.toString();
		Integer output = 1;
		String outputInJson = Skeleton.mapToJson(output);
		
		Mockito.when(adminService.deletePost(Mockito.anyInt())).thenReturn(output);
		
		this.mockMvc
				.perform(delete(URI))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(outputInJson));
	}
	
	
	@Test
	@WithMockUser(username="user", authorities = { "USER" })
	public void testLockUserAsUser() throws Exception {
		
		Integer userID = 10;
		String URI = "/admin/users/" + userID.toString() + "/lock";
		
		this.mockMvc
				.perform(post(URI))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username="admin", authorities = { "ADMIN", "USER" })
	public void testLockUserAsAdmin() throws Exception {
		
		Integer userID = 10;
		String URI = "/admin/users/" + userID.toString() + "/lock";
		
		Mockito.when(adminService.LockUser(Mockito.anyInt())).thenReturn(true);
		
		this.mockMvc
				.perform(post(URI))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
	}
	
	
	@Test
	@WithMockUser(username="user", authorities = { "USER" })
	public void testUnLockUserAsUser() throws Exception {
		
		Integer userID = 10;
		String URI = "/admin/users/" + userID.toString() + "/lock";
		
		this.mockMvc
				.perform(delete(URI))
				.andDo(print())
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username="admin", authorities = { "ADMIN", "USER" })
	public void testUnLockUserAsAdmin() throws Exception {
		
		Integer userID = 10;
		String URI = "/admin/users/" + userID.toString() + "/lock";
		
		Mockito.when(adminService.UnLockUser(Mockito.anyInt())).thenReturn(true);
		
		this.mockMvc
				.perform(delete(URI))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
	}
	
	@Test
	@WithMockUser(username="user", authorities = { "USER" })
	public void testDeleteAttachedPictureAsUser() throws Exception {
		
		Integer pictureID = 10;
		String URI = "/admin/pictures/attached/" + pictureID.toString();

		
		this.mockMvc
				.perform(delete(URI))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username="admin", authorities = { "ADMIN", "USER" })
	public void testDeleteAttachedPictureAsAdmin() throws Exception {
		
		Integer pictureID = 10;
		String URI = "/admin/pictures/attached/" + pictureID.toString();
		Integer output = 1;
		String outputInJson = Skeleton.mapToJson(output);
		
		Mockito.when(adminService.deleteAttachedPicture(Mockito.anyLong())).thenReturn(output);
		
		this.mockMvc
				.perform(delete(URI))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(outputInJson));
	}
	
	
	@Test
	@WithMockUser(username="user", authorities = { "USER" })
	public void testDeleteMainPictureAsUser() throws Exception {
		
		Integer pictureID = 10;
		String URI = "/admin/pictures/main/" + pictureID.toString();

		
		this.mockMvc
				.perform(delete(URI))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username="admin", authorities = { "ADMIN", "USER" })
	public void testDeleteMainPictureAsAdmin() throws Exception {
		
		Integer pictureID = 10;
		String URI = "/admin/pictures/main/" + pictureID.toString();
		Integer output = 1;
		String outputInJson = Skeleton.mapToJson(output);
		
		Mockito.when(adminService.deleteMainPicture(Mockito.anyLong())).thenReturn(output);
		
		this.mockMvc
				.perform(delete(URI))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json(outputInJson));
	}
	
	
	@Test
	@WithMockUser(username="user", authorities = { "USER" })
	public void testSendCircularEmailAsUser() throws Exception {
		
		String URI = "/admin/circularEmail";

		
		this.mockMvc
				.perform(post(URI))
				.andDo(print())
				.andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(username="admin", authorities = { "ADMIN", "USER" })
	public void testSendCircularEmailAsAdmin() throws Exception {
		
		String URI = "/admin/circularEmail";
		String inputInJson = Skeleton.mapToJson(circularEmail);
		
		Mockito.when(adminService.SendCircularEmail(Mockito.any(CircularEmail.class))).thenReturn(true);
		
		this.mockMvc
				.perform(post(URI)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputInJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
	}
}
