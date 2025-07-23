package org.webapp.hibernateapp.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "recives")
public class Recive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long total;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public Recive() {
    }

    public Recive(String description, Long total) {
        this.description = description;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Recive recive = (Recive) object;
        return Objects.equals(id, recive.id) && Objects.equals(description, recive.description) && Objects.equals(total, recive.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, total);
    }
}
