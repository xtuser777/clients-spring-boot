package br.dev.xt.clients.services;

import br.dev.xt.clients.entities.Address;
import br.dev.xt.clients.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public void create(Address address) {
        this.addressRepository.save(address);
    }

    @Transactional(readOnly = true)
    public Optional<Address> read(Integer id) {
        return this.addressRepository.findById(id);
    }

    @Transactional
    public void update(Address address) {
        this.addressRepository.save(address);
    }

    @Transactional
    public void delete(Integer id) {
        this.addressRepository.deleteById(id);
    }
}
