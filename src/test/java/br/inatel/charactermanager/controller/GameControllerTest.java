package br.inatel.charactermanager.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import br.inatel.charactermanager.controller.repository.GameRepository;
import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.model.Game;
import br.inatel.charactermanager.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class GameControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	public void beforeEach() {
		User player1 = new User();
		player1.setName("player1");
		player1.setEmail("player1@email.com");
		player1.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		User player2 = new User();
		player2.setName("player2");
		player2.setEmail("player2@email.com");
		player2.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		List<User> playersList = new ArrayList<>(Arrays.asList(player1, player2));
		
		Game game1 = new Game();
		game1.setName("game1");
		game1.setGameMaster(player1);
		game1.setPlayers(playersList);
		
		Game game2 = new Game();
		game2.setName("game2");
		game2.setGameMaster(player2);
		game2.setPlayers(playersList);
		
		userRepository.save(player1);
		userRepository.save(player2);
		
		gameRepository.save(game1);
		gameRepository.save(game2);
	}
	
	@Test
	public void shouldListAllGames() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/game").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldListAllGamesByGameMasterId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/game?gameMasterId=1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldNotListAllGamesByGameMasterId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/game?gameMasterId=99").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(404))
			.andExpect(MockMvcResultMatchers.jsonPath("$.field").value("gameMasterId"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("No games found for the game master informed id"));
	}
	
	@Test
	public void shouldListGameByGameId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/game/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
	}
	
	@Test
	public void shouldNotListGameByGameId() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/game/99").contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(404))
			.andExpect(MockMvcResultMatchers.jsonPath("$.field").value("gameId"))
			.andExpect(MockMvcResultMatchers.jsonPath("$.error").value("No game found with the informed id"));
	}
	
	@Test
	public void shouldCreateNewGame() throws Exception {
		JSONObject body = new JSONObject();
		body.put("name", "game3");
		body.put("gameMasterId", "1");
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/game").content(body.toString()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(201))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("game3"));
	}

}
