package com.project.one.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int person_id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, message = "Введите правильное имя")
    @Column(name = "name")
    private String name;

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    @Max(value = 2023, message = "Год рождения должен быть меньше 2024")
    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "owner")
    private List<Book> books;

    public Person() {

    }

    public Person(String name, int year) {
        this.name = name;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Person{" +
                "person_id=" + person_id +
                ", name='" + name + '\'' +
                ", year=" + year +
                ", books=" + books +
                '}';
    }
}
