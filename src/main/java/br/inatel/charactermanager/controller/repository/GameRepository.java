package br.inatel.charactermanager.controller.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.charactermanager.model.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

	Page<Game> findByGameMasterId(Long gameMasterId, Pageable pageable);
	
}
