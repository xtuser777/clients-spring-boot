package br.dev.xt.clients.repositories;

import br.dev.xt.clients.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
