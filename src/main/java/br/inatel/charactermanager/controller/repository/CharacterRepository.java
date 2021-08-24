package br.inatel.charactermanager.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.charactermanager.model.Character;

public interface CharacterRepository extends JpaRepository<Character, Long> {

}
