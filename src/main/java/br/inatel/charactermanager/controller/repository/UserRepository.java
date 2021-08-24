package br.inatel.charactermanager.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.charactermanager.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
