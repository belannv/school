package com.college.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Школа")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "name")
    private String name;
    
    @Column(name = "street")
    private String street;

    @Column(name = "number")
    private String number;

    @Column(name = "city")
    private String city;

    @Column(name = "phone number")
    private String phoneNumber;

    @Column(name = "director")
    private String director;

    // Связь: Одна Школа имеет много Классов
    @OneToMany(mappedBy = "school")
    private Set<SchoolClass> classes;

    // Связь: Одна Школа имеет много Учителей
    @OneToMany(mappedBy = "school")
    private Set<Teacher> teachers;

    // Пустой конструктор (обязателен для Hibernate)
    public School() {
    }

    // --- Геттеры и Сеттеры ---
    // (Вы можете сгенерировать их в своей IDE)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Set<SchoolClass> getClasses() {
        return classes;
    }

    public void setClasses(Set<SchoolClass> classes) {
        this.classes = classes;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
}