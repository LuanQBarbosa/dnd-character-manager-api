package br.inatel.charactermanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.inatel.charactermanager.controller.repository.CharacterRepository;
import br.inatel.charactermanager.controller.repository.GameRepository;
import br.inatel.charactermanager.controller.repository.UserRepository;
import br.inatel.charactermanager.model.Character;
import br.inatel.charactermanager.model.Game;
import br.inatel.charactermanager.model.Job;
import br.inatel.charactermanager.model.Race;
import br.inatel.charactermanager.model.User;

@Service
public class DatabaseService {
	
	@Autowired
	private GameRepository gameRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CharacterRepository characterRepository;
	
	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void populateDatabase() {
		User player1 = new User();
		player1.setName("player1");
		player1.setEmail("player1@email.com");
		player1.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		User player2 = new User();
		player2.setName("player2");
		player2.setEmail("player2@email.com");
		player2.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		User player3 = new User();
		player3.setName("player3");
		player3.setEmail("player3@email.com");
		player3.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		User player4 = new User();
		player4.setName("player4");
		player4.setEmail("player4@email.com");
		player4.setPassword(new BCryptPasswordEncoder().encode("123456"));
		
		List<User> playersList1 = new ArrayList<>(Arrays.asList(player1, player2, player3));
		List<User> playersList2 = new ArrayList<>(Arrays.asList(player1, player2, player3, player4));
		
		Game game1 = new Game();
		game1.setName("game1");
		game1.setGameMaster(player1);
		game1.setPlayers(playersList1);
		
		Game game2 = new Game();
		game2.setName("game2");
		game2.setGameMaster(player1);
		game2.setPlayers(playersList1);
		
		Game game3 = new Game();
		game3.setName("game3");
		game3.setGameMaster(player2);
		game3.setPlayers(playersList2);
		
		Game game4 = new Game();
		game4.setName("game4");
		game4.setGameMaster(player2);
		game4.setPlayers(playersList2);
		
		Character character1 = new Character();
		character1.setName("character1");
		character1.setRace(Race.ELF);
		character1.setJob(Job.RANGER);
		character1.setStrength(5);
		character1.setCharisma(5);
		character1.setDexterity(5);
		character1.setIntelligence(5);
		character1.setWisdom(5);
		character1.setConstitution(5);
		character1.getWeaponsIndex().add("shortsword");
		character1.getArmorsIndex().add("padded");
		character1.getItemsIndex().add("backpack");
		character1.setOwner(player1);
		character1.setGame(game3);
		
		Character character12 = new Character();
		character12.setName("character12");
		character12.setRace(Race.DRAGONBORN);
		character12.setJob(Job.BARBARIAN);
		character12.setStrength(5);
		character12.setCharisma(5);
		character12.setDexterity(5);
		character12.setIntelligence(5);
		character12.setWisdom(5);
		character12.setConstitution(5);
		character12.setOwner(player1);
		character12.setGame(game4);
		
		Character character2 = new Character();
		character2.setName("character2");
		character2.setRace(Race.HUMAN);
		character2.setJob(Job.PALADIN);
		character2.setStrength(5);
		character2.setCharisma(5);
		character2.setDexterity(5);
		character2.setIntelligence(5);
		character2.setWisdom(5);
		character2.setConstitution(5);
		character2.setOwner(player2);
		character2.setGame(game1);
		
		Character character22 = new Character();
		character22.setName("character22");
		character22.setRace(Race.HALF_ELF);
		character22.setJob(Job.MONK);
		character22.setStrength(5);
		character22.setCharisma(5);
		character22.setDexterity(5);
		character22.setIntelligence(5);
		character22.setWisdom(5);
		character22.setConstitution(5);
		character22.setOwner(player2);
		character22.setGame(game2);
		
		Character character3 = new Character();
		character3.setName("character3");
		character3.setRace(Race.TIEFLING);
		character3.setJob(Job.SORCERER);
		character3.setStrength(5);
		character3.setCharisma(5);
		character3.setDexterity(5);
		character3.setIntelligence(5);
		character3.setWisdom(5);
		character3.setConstitution(5);
		character3.setOwner(player3);
		character3.setGame(game1);
		
		Character character32 = new Character();
		character32.setName("character32");
		character32.setRace(Race.HALF_ORC);
		character32.setJob(Job.FIGHTER);
		character32.setStrength(5);
		character32.setCharisma(5);
		character32.setDexterity(5);
		character32.setIntelligence(5);
		character32.setWisdom(5);
		character32.setConstitution(5);
		character32.setOwner(player3);
		character32.setGame(game2);
		
		Character character4 = new Character();
		character4.setName("character4");
		character4.setRace(Race.DWARF);
		character4.setJob(Job.CLERIC);
		character4.setStrength(5);
		character4.setCharisma(5);
		character4.setDexterity(5);
		character4.setIntelligence(5);
		character4.setWisdom(5);
		character4.setConstitution(5);
		character4.setOwner(player4);
		character4.setGame(game4);
		
		Character character42 = new Character();
		character42.setName("character42");
		character42.setRace(Race.DWARF);
		character42.setJob(Job.CLERIC);
		character42.setStrength(5);
		character42.setCharisma(5);
		character42.setDexterity(5);
		character42.setIntelligence(5);
		character42.setWisdom(5);
		character42.setConstitution(5);
		character42.setOwner(player4);
		character42.setGame(game3);
		
		List<Character> gameCharactersList1 = new ArrayList<>(Arrays.asList(character2, character3));
		game1.setCharacters(gameCharactersList1);
		
		List<Character> gameCharactersList2 = new ArrayList<>(Arrays.asList(character22, character32));
		game2.setCharacters(gameCharactersList2);
		
		List<Character> gameCharactersList3 = new ArrayList<>(Arrays.asList(character1, character42));
		game3.setCharacters(gameCharactersList3);
		
		List<Character> gameCharactersList4 = new ArrayList<>(Arrays.asList(character12, character4));
		game4.setCharacters(gameCharactersList4);
		
		List<Game> ownedGamesList1 = new ArrayList<>(Arrays.asList(game1, game2));
		List<Game> ownedGamesList2 = new ArrayList<>(Arrays.asList(game3, game4));
		
		List<Game> playingGamesList1 = new ArrayList<>(Arrays.asList(game3, game4));
		List<Game> playingGamesList2 = new ArrayList<>(Arrays.asList(game1, game2, game3, game4));
		
		List<Character> charactersList1 = new ArrayList<>(Arrays.asList(character1, character12));
		player1.setCharacters(charactersList1);
		player1.setOwnedGames(ownedGamesList1);
		player1.setPlayingGames(playingGamesList2);
		
		List<Character> charactersList2 = new ArrayList<>(Arrays.asList(character2, character22));
		player2.setCharacters(charactersList2);
		player2.setOwnedGames(ownedGamesList2);
		player2.setPlayingGames(playingGamesList2);
		
		List<Character> charactersList3 = new ArrayList<>(Arrays.asList(character3, character32));
		player3.setCharacters(charactersList3);
		player3.setPlayingGames(playingGamesList2);
		
		List<Character> charactersList4 = new ArrayList<>(Arrays.asList(character4, character42));
		player4.setCharacters(charactersList4);
		player4.setPlayingGames(playingGamesList1);
		
		userRepository.save(player1);
		userRepository.save(player2);
		userRepository.save(player3);
		userRepository.save(player4);
		
		characterRepository.save(character1);
		characterRepository.save(character12);
		characterRepository.save(character2);
		characterRepository.save(character22);
		characterRepository.save(character3);
		characterRepository.save(character32);
		characterRepository.save(character4);
		characterRepository.save(character42);
		
		gameRepository.save(game1);
		gameRepository.save(game2);
		gameRepository.save(game3);
		gameRepository.save(game4);
	}

}
