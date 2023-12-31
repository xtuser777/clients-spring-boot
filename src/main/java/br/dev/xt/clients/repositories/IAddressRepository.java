package br.dev.xt.clients.repositories;

import br.dev.xt.clients.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Integer> {
}
