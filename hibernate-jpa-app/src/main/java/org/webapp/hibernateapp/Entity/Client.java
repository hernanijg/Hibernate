package org.webapp.hibernateapp.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;



    @Column(name = "form_page")
    private String formPage;

    public Client() {
    }

    public Client(Long id, String name, String lastname, String formPage) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.formPage = formPage;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFormPage() {
        return formPage;
    }

    public void setFormPage(String formPage) {
        this.formPage = formPage;
    }



    @Override
    public String toString() {
        return "Id: " + this.id +
                " Name: " + this.name +
                " LastName: " + this.lastname +
                " FormPage: " + this.formPage;

    }
}
