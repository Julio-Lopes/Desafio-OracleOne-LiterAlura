package com.aluraDesafio.literatura.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.aluraDesafio.literatura.Service.PersonService;

@Controller
public class PersonController {
    
    @Autowired
    PersonService service;

    public void listaTodosAutores(){
        service.listaTodosAutores();
    }

    public void listaAutoresVivoNoAno(int ano){
        service.listaAutoresVivoNoAno(ano);
    }

    public void buscaPorNomeAutor(String nome){
        service.buscaPorNomeAutor(nome);
    }
}
