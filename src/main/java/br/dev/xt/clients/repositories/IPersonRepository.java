package br.dev.xt.clients.repositories;

import br.dev.xt.clients.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person, Integer> {
}
