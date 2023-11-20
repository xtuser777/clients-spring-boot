package br.dev.xt.clients.services;

import br.dev.xt.clients.entities.EnterprisePerson;
import br.dev.xt.clients.repositories.EnterprisePersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EnterprisePersonService {

    private final EnterprisePersonRepository enterprisePersonRepository;

    @Autowired
    public EnterprisePersonService(EnterprisePersonRepository enterprisePersonRepository) {
        this.enterprisePersonRepository = enterprisePersonRepository;
    }

    @Transactional
    public void create(EnterprisePerson person) {
        this.enterprisePersonRepository.save(person);
    }

    @Transactional(readOnly = true)
    public Optional<EnterprisePerson> read(Integer id) {
        return this.enterprisePersonRepository.findById(id);
    }

    @Transactional
    public void update(EnterprisePerson person) {
        this.enterprisePersonRepository.save(person);
    }

    @Transactional
    public void delete(Integer id) {
        this.enterprisePersonRepository.deleteById(id);
    }
}
