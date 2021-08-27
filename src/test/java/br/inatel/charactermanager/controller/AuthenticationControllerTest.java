package br.inatel.charactermanager.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("authTest")
public class AuthenticationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	public void beforeEach() {
		User player = new User();
		player.setName("player");
		player.setEmail("player@email.com");
		player.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		userRepository.save(player);
	}

	@Test
	public void shouldLogin() throws Exception {		
		JSONObject body = new JSONObject();
		body.put("email", "player@email.com");
		body.put("password", "123456");

		mockMvc.perform(MockMvcRequestBuilders
				.post("/auth")
				.content(body.toString())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldNotLogin() throws Exception {		
		JSONObject body = new JSONObject();
		body.put("email", "wrong@email.com");
		body.put("password", "123456");

		mockMvc.perform(MockMvcRequestBuilders
				.post("/auth")
				.content(body.toString())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(400));
	}

}
