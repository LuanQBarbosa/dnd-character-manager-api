package br.inatel.charactermanager.repository;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import br.inatel.charactermanager.controller.repository.GameRepository;
import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.model.Game;
import br.inatel.charactermanager.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GameRepositoryTest {
	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	void beforeEach() {
		User player1 = new User();
		player1.setName("player1");
		player1.setEmail("player1@email.com");
		player1.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		userRepository.save(player1);
		
		Game game1 = new Game();
		game1.setName("game1");
		game1.setGameMaster(player1);
		
		gameRepository.save(game1);
	}
	
	@Test
	public void shouldFindGame() {
		Pageable pageable = PageRequest.of(0, 1);
		Long gameMasterId = (long) 1;
		Page<Game> game = gameRepository.findAllByGameMasterId(gameMasterId, pageable);

		Assert.assertNotNull(game.get().findFirst());
		Assert.assertEquals(gameMasterId, game.get().findFirst().get().getId());
	}
	
	@Test
	public void shouldNotFindGame() {
		Pageable pageable = PageRequest.of(0, 1);
		Long gameMasterId = (long) 99;
		Page<Game> game = gameRepository.findAllByGameMasterId(gameMasterId, pageable);

		Assert.assertFalse(game.get().findFirst().isPresent());
	}

}
