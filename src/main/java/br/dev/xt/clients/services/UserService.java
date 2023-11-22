package br.dev.xt.clients.services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.dev.xt.clients.dto.user.UserCreateDTO;
import br.dev.xt.clients.dto.user.UserDTO;
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

    private final IUserRepository userRepository;
    private final IAddressRepository addressRepository;
    private final IContactRepository contactRepository;
    private final IIndividualPersonRepository individualPersonRepository;
    private final IPersonRepository personRepository;

    @Autowired
    public UserService(IUserRepository userRepository, IAddressRepository addressRepository, IContactRepository contactRepository, IIndividualPersonRepository individualPersonRepository, IPersonRepository personRepository) {
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
            var passwordHash = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(passwordHash);
            User entity = this.userRepository.save(user);
            entity.setPassword("");

            return new UserResponseDTO(true, new ArrayList<>(), entity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new UserResponseDTO(false, errors, null);
        }
    }

    public List<UserDTO> read() {
        try {
            return this.userRepository.findAll().stream().map(UserDTO::new).toList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

    public UserDTO read(Integer id) {
        try {
            Optional<User> entity = this.userRepository.findById(id);
            User user = null;
            if (entity.isPresent()) user = entity.get();
            if (user == null) return null;
            return new UserDTO(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Transactional
    public UserResponseDTO update(Integer id, UserUpdateDTO userDTO) {
        try {
            User user = userDTO.toEntity(this.userRepository.findById(id).get());
            Address address = this.addressRepository.save(user.getPerson().getAddress());
            Contact contact = this.contactRepository.save(user.getPerson().getContact());
            IndividualPerson individualPerson = this.individualPersonRepository.save(user.getPerson().getIndividual());
            Person person = this.personRepository.save(user.getPerson());
            var passwordHash = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(passwordHash);
            User entity = this.userRepository.save(user);
            entity.setPassword("");

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
            user.get().setPassword("");

            return new UserResponseDTO(true, new ArrayList<>(), user.get());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new UserResponseDTO(false, errors, null);
        }
    }
}
