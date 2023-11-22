package br.dev.xt.clients.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer type;

    @OneToOne(targetEntity = IndividualPerson.class)
    private IndividualPerson individual;

    @OneToOne(targetEntity = EnterprisePerson.class)
    private EnterprisePerson enterprise;

    @OneToOne(targetEntity = Address.class, optional = false)
    private Address address;

    @OneToOne(targetEntity = Contact.class, optional = false)
    private Contact contact;

    public Person() {}

    public Person(Integer id, Integer type, IndividualPerson individual, EnterprisePerson enterprise, Address address, Contact contact) {
        this.id = id;
        this.type = type;
        this.individual = individual;
        this.enterprise = enterprise;
        this.address = address;
        this.contact = contact;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public IndividualPerson getIndividual() {
        return individual;
    }

    public void setIndividual(IndividualPerson individual) {
        this.individual = individual;
    }

    public EnterprisePerson getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterprisePerson enterprise) {
        this.enterprise = enterprise;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(type, person.type) && Objects.equals(individual, person.individual) && Objects.equals(enterprise, person.enterprise) && Objects.equals(address, person.address) && Objects.equals(contact, person.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, individual, enterprise, address, contact);
    }
}
