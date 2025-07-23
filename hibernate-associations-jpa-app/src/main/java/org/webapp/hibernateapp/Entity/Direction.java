package org.webapp.hibernateapp.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "directions")
public class Direction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private Integer num;

    public Direction() {
    }

    public Direction(String street, Integer num) {
        this.street = street;
        this.num = num;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "id:" + id + " street: " + street + " num: " + num;
    }
}
