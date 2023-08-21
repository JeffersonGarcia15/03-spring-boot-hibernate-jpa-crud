package com.jefferson.cruddemo.entity;

import jakarta.persistence.*;

@Entity // maps the class to a database table
@Table(name = "student")
public class Student {

    // define fields
    @Id // maps the field to the primary key column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment. Let MYSQL handle it.
    @Column(name = "id") // maps the field to the actual column name
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    // define constructors
    public Student() {

    }

    public Student(String firstName, String lastName, String email) { // no id because it's auto-incremented
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // define getters/setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // define toString() method
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
