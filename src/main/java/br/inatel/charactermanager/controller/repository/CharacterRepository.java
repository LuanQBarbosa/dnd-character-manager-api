package br.inatel.charactermanager.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.charactermanager.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
	
	List<Character> findAllByGameId(Long gameId);
	
	List<Character> findAllByOwnerId(Long ownerId);
	
	List<Character> findAllByGameIdAndOwnerId(Long gameId, Long ownerId);

}
