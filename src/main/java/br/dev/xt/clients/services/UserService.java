package br.dev.xt.clients.services;

import br.dev.xt.clients.dto.user.UserCreateDTO;
import br.dev.xt.clients.dto.user.UserResponseDTO;
import br.dev.xt.clients.dto.user.UserUpdateDTO;
import br.dev.xt.clients.entities.*;
import br.dev.xt.clients.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final IndividualPersonRepository individualPersonRepository;
    private final PersonRepository personRepository;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository, ContactRepository contactRepository, IndividualPersonRepository individualPersonRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
        this.individualPersonRepository = individualPersonRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public UserResponseDTO create(UserCreateDTO userDTO) {
        User user = userDTO.toEntity();
        try {
            Address address = this.addressRepository.save(user.getPerson().getAddress());
            Contact contact = this.contactRepository.save(user.getPerson().getContact());
            IndividualPerson individualPerson = this.individualPersonRepository.save(user.getPerson().getIndividual());
            user.getPerson().setIndividual(individualPerson);
            user.getPerson().setContact(contact);
            user.getPerson().setAddress(address);
            Person person = this.personRepository.save(user.getPerson());
            user.setPerson(person);
            User entity = this.userRepository.save(user);

            return new UserResponseDTO(true, new ArrayList<>(), entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new UserResponseDTO(false, errors, null);
        }
    }

    public List<User> read() {
        return this.userRepository.findAll();
    }

    public Optional<User> read(Integer id) {
        return this.userRepository.findById(id);
    }

    @Transactional
    public UserResponseDTO update(Integer id, UserUpdateDTO userDTO) {
        try {
            User user = userDTO.toEntity(this.userRepository.findById(id).get());
            Address address = this.addressRepository.save(user.getPerson().getAddress());
            Contact contact = this.contactRepository.save(user.getPerson().getContact());
            IndividualPerson individualPerson = this.individualPersonRepository.save(user.getPerson().getIndividual());
            Person person = this.personRepository.save(user.getPerson());
            User entity = this.userRepository.save(user);

            return new UserResponseDTO(true, new ArrayList<>(), entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new UserResponseDTO(false, errors, null);
        }
    }

    @Transactional
    public UserResponseDTO delete(Integer id) {
        try {
            Optional<User> user = this.userRepository.findById(id);
            if (user.isEmpty()) {
                List<String> errors = new ArrayList<>();
                errors.add("User not found.");
                return new UserResponseDTO(false, errors, null);
            }

            this.userRepository.deleteById(id);
            this.personRepository.deleteById(user.get().getPerson().getId());
            this.individualPersonRepository.deleteById(user.get().getPerson().getIndividual().getId());
            this.contactRepository.deleteById(user.get().getPerson().getContact().getId());
            this.addressRepository.deleteById(user.get().getPerson().getAddress().getId());

            return new UserResponseDTO(true, new ArrayList<>(), user.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new UserResponseDTO(false, errors, null);
        }
    }
}
