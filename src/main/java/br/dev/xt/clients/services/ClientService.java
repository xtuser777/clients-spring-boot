package br.dev.xt.clients.services;

import br.dev.xt.clients.dto.client.ClientResponseDTO;
import br.dev.xt.clients.entities.*;
import br.dev.xt.clients.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final IndividualPersonRepository individualPersonRepository;
    private final EnterprisePersonRepository enterprisePersonRepository;
    private final PersonRepository personRepository;

    @Autowired
    public ClientService(
        ClientRepository clientRepository,
        AddressRepository addressRepository,
        ContactRepository contactRepository,
        IndividualPersonRepository individualPersonRepository,
        EnterprisePersonRepository enterprisePersonRepository,
        PersonRepository personRepository
    ) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
        this.individualPersonRepository = individualPersonRepository;
        this.enterprisePersonRepository = enterprisePersonRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public ClientResponseDTO save(Client client) {
        try {
            Address address = this.addressRepository.save(client.getPerson().getAddress());
            Contact contact = this.contactRepository.save(client.getPerson().getContact());
            Person person = new Person();
            if (client.getPerson().getType() == 1) {
                IndividualPerson individualPerson = this.individualPersonRepository.save(client.getPerson().getIndividual());
                client.getPerson().setIndividual(individualPerson);
            } else {
                EnterprisePerson enterprisePerson = this.enterprisePersonRepository.save(client.getPerson().getEnterprise());
                client.getPerson().setEnterprise(enterprisePerson);
            }
            client.getPerson().setContact(contact);
            client.getPerson().setAddress(address);
            person = this.personRepository.save(client.getPerson());
            client.setPerson(person);
            this.clientRepository.save(client);

            return new ClientResponseDTO(true, new ArrayList<>(), client);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ClientResponseDTO(false, errors, null);
        }
    }

    public List<Client> findAll() {
        return this.clientRepository.findAll();
    }

    public Optional<Client> findById(Integer id) {
        return this.clientRepository.findById(id);
    }

    @Transactional
    public ClientResponseDTO update(Integer id, Client client) {
        try {
            Address address = this.addressRepository.save(client.getPerson().getAddress());
            Contact contact = this.contactRepository.save(client.getPerson().getContact());
            if (client.getPerson().getType() == 1) {
                IndividualPerson individualPerson = this.individualPersonRepository.save(client.getPerson().getIndividual());
            } else {
                EnterprisePerson enterprisePerson = this.enterprisePersonRepository.save(client.getPerson().getEnterprise());
            }
            Person person = this.personRepository.save(client.getPerson());
            client.setId(id);
            this.clientRepository.save(client);

            return new ClientResponseDTO(true, new ArrayList<>(), client);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ClientResponseDTO(false, errors, null);
        }
    }

    @Transactional
    public ClientResponseDTO delete(Integer id) {
        try {
            Optional<Client> c = this.clientRepository.findById(id);
            if (c.isEmpty()) {
                List<String> errors = new ArrayList<>();
                errors.add("Client not found.");
                return new ClientResponseDTO(false, errors, null);
            }

            this.clientRepository.deleteById(id);
            this.personRepository.deleteById(c.get().getPerson().getId());
            if (c.get().getPerson().getType() == 1)
                this.individualPersonRepository.deleteById(c.get().getPerson().getIndividual().getId());
            else
                this.enterprisePersonRepository.deleteById(c.get().getPerson().getEnterprise().getId());
            this.contactRepository.deleteById(c.get().getPerson().getContact().getId());
            this.addressRepository.deleteById(c.get().getPerson().getAddress().getId());

            return new ClientResponseDTO(true, new ArrayList<>(), c.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ClientResponseDTO(false, errors, null);
        }
    }
}
