package org.webapp.hibernateapp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "client_details")
public class ClientDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean prime;

    @Column(name = "count_points")
    private Long countPoints;

    @OneToOne
    @JoinColumn(name = "client_detail_id")
    Client client;

    public ClientDetail() {
    }

    public ClientDetail(boolean prime, Long countPoints) {
        this.prime = prime;
        this.countPoints = countPoints;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isPrime() {
        return prime;
    }

    public void setPrime(boolean prime) {
        this.prime = prime;
    }

    public Long getCountPoints() {
        return countPoints;
    }

    public void setCountPoints(Long countPoints) {
        this.countPoints = countPoints;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "ClientDetail{" +
                "id=" + id +
                ", prime=" + prime +
                ", countPoints=" + countPoints +
                '}';
    }
}
