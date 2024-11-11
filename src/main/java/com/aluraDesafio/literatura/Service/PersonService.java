package com.aluraDesafio.literatura.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluraDesafio.literatura.Repository.PersonRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void listaTodosAutores() {
        personRepository.listaTodosAutores().forEach(person -> {
            System.out.println(person);
        });
    }

    @Transactional
    public void listaAutoresVivoNoAno(int ano) {
        personRepository.listaAutoresVivoNoAno(ano).forEach(person -> {
            System.out.println(person);
        });
    }

    @Transactional
    public void buscaPorNomeAutor(String nome){
        personRepository.buscaPorNome(nome).forEach(person -> {
            System.out.println(person);
        });
    }
}