package br.dev.xt.clients.services;

import br.dev.xt.clients.entities.IndividualPerson;
import br.dev.xt.clients.repositories.IndividualPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IndividualPersonService {

    private final IndividualPersonRepository individualPersonRepository;

    @Autowired
    public IndividualPersonService(IndividualPersonRepository individualPersonRepository) {
        this.individualPersonRepository = individualPersonRepository;
    }

    @Transactional
    public void create(IndividualPerson person) {
        this.individualPersonRepository.save(person);
    }

    @Transactional(readOnly = true)
    public Optional<IndividualPerson> read(Integer id) {
        return this.individualPersonRepository.findById(id);
    }

    @Transactional
    public void update(IndividualPerson person) {
        this.individualPersonRepository.save(person);
    }

    @Transactional
    public void delete(Integer id) {
        this.individualPersonRepository.deleteById(id);
    }
}
