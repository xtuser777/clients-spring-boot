package br.dev.xt.clients.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 14, nullable = false)
    private String phone;
    
    @Column(length = 15, nullable = false)
    private String cellphone;
    
    @Column(length = 80, nullable = false)
    private String email;

    public Contact() {}

    public Contact(
        Integer id,
        String phone,
        String cellphone,
        String email
    ) {
        this.id = id;
        this.phone = phone;
        this.cellphone = cellphone;
        this.email = email;
    }
}