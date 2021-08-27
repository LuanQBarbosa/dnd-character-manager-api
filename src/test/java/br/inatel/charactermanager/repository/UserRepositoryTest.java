package br.inatel.charactermanager.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@BeforeEach
	void beforeEach() {
		User player1 = new User();
		player1.setName("player1");
		player1.setEmail("player1@email.com");
		player1.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		userRepository.save(player1);
	}
	
	@Test
	public void shouldFindUser() {
		String email = "player1@email.com";
		Optional<User> user = userRepository.findByEmail(email);

		Assert.assertNotNull(user.get());
		Assert.assertEquals(email, user.get().getEmail());
	}
	
	@Test
	public void shouldNotFindUser() {
		String email = "player99@email.com";
		Optional<User> user = userRepository.findByEmail(email);

		Assert.assertFalse(user.isPresent());
	}

}
