package br.dev.xt.clients.repositories;

import br.dev.xt.clients.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
