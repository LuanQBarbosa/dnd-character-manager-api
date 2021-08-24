package br.inatel.charactermanager.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.charactermanager.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {
	
	List<Character> findByGameId(Long gameId);
	
	List<Character> findByGameIdAndOwnerId(Long gameId, Long ownerId);

}
