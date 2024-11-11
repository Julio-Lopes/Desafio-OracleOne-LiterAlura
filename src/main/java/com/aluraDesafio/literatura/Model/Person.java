package com.aluraDesafio.literatura.Model;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int birth_year;
    private int death_year;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "book_authors",
        joinColumns = @JoinColumn(name = "person_id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> books;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public int getBirth_year() {
        return birth_year;
    }
    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }
    public int getDeath_year() {
        return death_year;
    }
    public void setDeath_year(int death_year) {
        this.death_year = death_year;
    }

    public Person(long id, String name, int birth_year, int death_year, List<Book> books) {
        this.id = id;
        this.name = name;
        this.birth_year = birth_year;
        this.death_year = death_year;
        this.books = books;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return  "\n" + "-----Autor-----\n" +
                "Autor: " + this.name + "\n" +
                "Ano de nascimento: " + this.birth_year + "\n" +
                "Ano de falecimento: " + this.death_year + "\n" +
                "Livros: " + this.books.stream()
                                        .map(Book::getTitle)
                                        .collect(Collectors.joining(", ")) + "\n" +
                "----------------" + "\n";
    }

}