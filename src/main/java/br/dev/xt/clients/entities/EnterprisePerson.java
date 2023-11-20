package br.dev.xt.clients.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "enterprise_person")
public class EnterprisePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "corporate_name", length = 80, nullable = false)
    private String corporateName;

    @Column(name = "fantasy_name", length = 50, nullable = false)
    private String fantasyName;

    @Column(length = 19, nullable = false)
    private String document;

    public EnterprisePerson() {}

    public EnterprisePerson(Integer id, String corporateName, String fantasyName, String document) {
        this.id = id;
        this.corporateName = corporateName;
        this.fantasyName = fantasyName;
        this.document = document;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
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
