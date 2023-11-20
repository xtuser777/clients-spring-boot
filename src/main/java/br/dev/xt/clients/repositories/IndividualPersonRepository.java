package br.dev.xt.clients.repositories;

import br.dev.xt.clients.entities.IndividualPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndividualPersonRepository extends JpaRepository<IndividualPerson, Integer> {
}
