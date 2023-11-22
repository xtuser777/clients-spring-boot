package br.dev.xt.clients.dto.user;

import br.dev.xt.clients.entities.Person;
import br.dev.xt.clients.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

public class UserDTO {

    private Integer id;
    private String username;
    private Person person;

    public UserDTO(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.person = entity.getPerson();
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Person getPerson() {
        return person;
    }
}
