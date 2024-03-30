package br.com.rafaellbarros.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rafaellbarros.domain.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
