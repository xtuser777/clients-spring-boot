package br.dev.xt.clients.repositories;

import br.dev.xt.clients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Integer> {
}
