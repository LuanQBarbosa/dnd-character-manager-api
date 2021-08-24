package br.inatel.charactermanager.controller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.charactermanager.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	List<Game> findByGameMasterId(Long gameMasterId);
	
}
