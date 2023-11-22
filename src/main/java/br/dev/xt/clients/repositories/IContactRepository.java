package br.dev.xt.clients.repositories;

import br.dev.xt.clients.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactRepository extends JpaRepository<Contact, Integer> {
}
