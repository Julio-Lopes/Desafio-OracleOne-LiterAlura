package com.aluraDesafio.literatura.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aluraDesafio.literatura.Model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b JOIN FETCH b.authors")
    List<Book> listaTodosLivros();

    @Query("SELECT b FROM Book b JOIN FETCH b.authors WHERE b.languages > :idioma")
    List<Book> listaLivrosPorIdioma(@Param("idioma") String idioma);

}