package org.webapp.hibernateapp.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(
            name="tbl_clients_directions",
            joinColumns = @JoinColumn(name = "id_client"),
            inverseJoinColumns = @JoinColumn(name = "id_direction"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_direction"})
    )
    private List<Direction> directions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private List<Recive> recive;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private ClientDetail detail;

    public Client() {
        recive = new ArrayList<>();
        directions = new ArrayList<>();
    }

    public Client(String name, String lastname) {
        this();
        this.name = name;
        this.lastname = lastname;
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

    public ClientDetail getDetail() {
        return detail;
    }

    public void setDetail(ClientDetail detail) {
        this.detail = detail;
    }

    public List<Direction> getDirections() {
        return directions;
    }

    public void setDirections(List<Direction> directions) {
        this.directions = directions;
    }

    public List<Recive> getRecive() {
        return recive;
    }

    public void setRecive(List<Recive> recive) {
        this.recive = recive;
    }

    public void addRecive(Recive recive){
        this.recive.add(recive);
        recive.setClient(this);
    }

    public void removeRecive(Recive recive){
        this.recive.remove(recive);
        recive.setClient(null);
    }


    @Override
    public String toString() {
        return "Id: " + this.id +
                " Name: " + this.name +
                " LastName: " + this.lastname +
                " FormPage: " + this.formPage;

    }
}
