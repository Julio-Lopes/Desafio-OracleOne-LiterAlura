package com.aluraDesafio.literatura.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.aluraDesafio.literatura.Service.BookService;

@Controller
public class BookController {
    
    @Autowired
    private BookService service;

    public void salvaLivroPorTitulo(String title){
        service.salvaLivroPorTitulo(title);
    }

    public void listaTodosLivros() {
        service.listaTodosLivros();
    }

    public void listaLivrosPorIdioma(String idioma){
        service.listaLivrosPorIdioma(idioma);
    }
}
