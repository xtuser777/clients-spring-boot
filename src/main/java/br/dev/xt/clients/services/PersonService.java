package br.dev.xt.clients.services;

import br.dev.xt.clients.entities.Person;
import br.dev.xt.clients.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void create(Person person) {
        this.personRepository.save(person);
    }

    @Transactional(readOnly = true)
    public Optional<Person> read(Integer id) {
        return this.personRepository.findById(id);
    }

    @Transactional
    public void update(Person person) {
        this.personRepository.save(person);
    }

    @Transactional
    public void delete(Integer id) {
        this.personRepository.deleteById(id);
    }
}
