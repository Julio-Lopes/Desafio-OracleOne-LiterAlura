package com.aluraDesafio.literatura.Model;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "book_authors",
        joinColumns = @JoinColumn(name = "book_id"),
        inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> authors;

    private List<String> languages;
    private int download_count;

    public Book(long id, String title, List<Person> authors, List<String> languages, int download_count) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.download_count = download_count;
    }

    public Book(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Person> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Person> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public int getDownload_count() {
        return download_count;
    }

    public void setDownload_count(int download_count) {
        this.download_count = download_count;
    }

    @Override
    public String toString() {
        return "\n" + "-----Livro-----\n" +
               "Titulo: " + title + "\n" +
               "Autor: " + authors.get(0).getName() + "\n" +
               "Idioma: " + languages.stream()
                                    .map(Object::toString) 
                                    .collect(Collectors.joining(", ")) + "\n" +
               "Número de downloads: " + download_count + "\n" +
               "----------------" + "\n";
    }
}