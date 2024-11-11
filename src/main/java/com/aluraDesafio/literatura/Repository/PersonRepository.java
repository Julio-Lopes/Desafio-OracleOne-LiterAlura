package com.aluraDesafio.literatura.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aluraDesafio.literatura.Model.Person;
import java.util.List;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByName(String name);

    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.books")
    List<Person> listaTodosAutores();

    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.books WHERE p.death_year > :ano")
    List<Person> listaAutoresVivoNoAno(@Param("ano") Integer ano);

    @Query("SELECT p FROM Person p LEFT JOIN FETCH p.books WHERE p.name LIKE %:nome%")
    List<Person> buscaPorNome(@Param("nome") String nome);
}