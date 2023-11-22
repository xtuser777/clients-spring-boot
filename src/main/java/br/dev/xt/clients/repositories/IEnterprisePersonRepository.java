package br.dev.xt.clients.repositories;

import br.dev.xt.clients.entities.EnterprisePerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEnterprisePersonRepository extends JpaRepository<EnterprisePerson, Integer> {
}
