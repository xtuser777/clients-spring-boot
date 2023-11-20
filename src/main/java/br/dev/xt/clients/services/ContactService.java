package br.dev.xt.clients.services;

import br.dev.xt.clients.entities.Contact;
import br.dev.xt.clients.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Transactional
    public void create(Contact contact) {
        this.contactRepository.save(contact);
    }

    @Transactional(readOnly = true)
    public Optional<Contact> read(Integer id) {
        return this.contactRepository.findById(id);
    }

    @Transactional
    public void update(Contact contact) {
        this.contactRepository.save(contact);
    }

    @Transactional
    public void delete(Integer id) {
        this.contactRepository.deleteById(id);
    }
}
