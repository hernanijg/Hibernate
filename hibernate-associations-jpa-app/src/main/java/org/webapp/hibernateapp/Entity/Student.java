package org.webapp.hibernateapp.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private  String name;
    private  String lastname;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tbl_students_curses", joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns =  @JoinColumn(name = "curse_id"),
        uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "curse_id"})
    )
    private  List<Curse> curses;

    public Student() {
        curses = new ArrayList<>();
    }

    public Student(String name, String lastname) {
        this();
        this.name = name;
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Curse> getCurses() {
        return curses;
    }

    public void setCurses(List<Curse> curses) {
        this.curses = curses;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", curses=" + curses +
                '}';
    }
}
