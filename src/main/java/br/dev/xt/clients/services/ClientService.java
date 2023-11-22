package br.dev.xt.clients.services;

import br.dev.xt.clients.dto.client.*;
import br.dev.xt.clients.entities.*;
import br.dev.xt.clients.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final IClientRepository clientRepository;
    private final IAddressRepository addressRepository;
    private final IContactRepository contactRepository;
    private final IIndividualPersonRepository individualPersonRepository;
    private final IEnterprisePersonRepository enterprisePersonRepository;
    private final IPersonRepository personRepository;

    @Autowired
    public ClientService(
        IClientRepository clientRepository,
        IAddressRepository addressRepository,
        IContactRepository contactRepository,
        IIndividualPersonRepository individualPersonRepository,
        IEnterprisePersonRepository enterprisePersonRepository,
        IPersonRepository personRepository
    ) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
        this.individualPersonRepository = individualPersonRepository;
        this.enterprisePersonRepository = enterprisePersonRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public ClientResponseDTO save(ClientCreateIndividualDTO clientDTO) {
        Client client = clientDTO.toEntity();
        try {
            Address address = this.addressRepository.save(client.getPerson().getAddress());
            Contact contact = this.contactRepository.save(client.getPerson().getContact());
            Person person = new Person();
            IndividualPerson individualPerson = this.individualPersonRepository.save(client.getPerson().getIndividual());
            client.getPerson().setIndividual(individualPerson);
            client.getPerson().setContact(contact);
            client.getPerson().setAddress(address);
            person = this.personRepository.save(client.getPerson());
            client.setPerson(person);
            Client entity = this.clientRepository.save(client);

            return new ClientResponseDTO(true, new ArrayList<>(), entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ClientResponseDTO(false, errors, null);
        }
    }

    @Transactional
    public ClientResponseDTO save(ClientCreateEnterpriseDTO clientDTO) {
        Client client = clientDTO.toEntity();
        try {
            Address address = this.addressRepository.save(client.getPerson().getAddress());
            Contact contact = this.contactRepository.save(client.getPerson().getContact());
            Person person = new Person();
            EnterprisePerson enterprisePerson = this.enterprisePersonRepository.save(client.getPerson().getEnterprise());
            client.getPerson().setEnterprise(enterprisePerson);
            client.getPerson().setContact(contact);
            client.getPerson().setAddress(address);
            person = this.personRepository.save(client.getPerson());
            client.setPerson(person);
            Client entity = this.clientRepository.save(client);

            return new ClientResponseDTO(true, new ArrayList<>(), entity);
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
    public ClientResponseDTO update(Integer id, ClientUpdateIndividualDTO client) {
        try {
            Client c = client.toEntity(this.clientRepository.findById(id).get());

            Address address = this.addressRepository.save(c.getPerson().getAddress());
            Contact contact = this.contactRepository.save(c.getPerson().getContact());
            IndividualPerson individualPerson = this.individualPersonRepository.save(c.getPerson().getIndividual());
            Person person = this.personRepository.save(c.getPerson());
            Client entity = this.clientRepository.save(c);

            return new ClientResponseDTO(true, new ArrayList<>(), entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ClientResponseDTO(false, errors, null);
        }
    }

    @Transactional
    public ClientResponseDTO update(Integer id, ClientUpdateEnterpriseDTO client) {
        try {
            Client c = client.toEntity(this.clientRepository.findById(id).get());

            Address address = this.addressRepository.save(c.getPerson().getAddress());
            Contact contact = this.contactRepository.save(c.getPerson().getContact());
            EnterprisePerson enterprisePerson = this.enterprisePersonRepository.save(c.getPerson().getEnterprise());
            Person person = this.personRepository.save(c.getPerson());
            Client entity = this.clientRepository.save(c);

            return new ClientResponseDTO(true, new ArrayList<>(), entity);
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
