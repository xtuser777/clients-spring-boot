package br.dev.xt.clients.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "individual_person")
public class IndividualPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 80, nullable = false)
    private String name;

    @Column(length = 14, nullable = false)
    private String document;

    @Column(columnDefinition = "date", nullable = false)
    private Date birth;

    public IndividualPerson() {}

    public IndividualPerson(Integer id, String name, String document, Date birth) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
