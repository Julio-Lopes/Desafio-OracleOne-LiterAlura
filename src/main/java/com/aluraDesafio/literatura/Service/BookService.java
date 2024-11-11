package com.aluraDesafio.literatura.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aluraDesafio.literatura.DTO.BookResponse;
import com.aluraDesafio.literatura.Model.Book;
import com.aluraDesafio.literatura.Model.Person;
import com.aluraDesafio.literatura.Repository.BookRepository;
import com.aluraDesafio.literatura.Repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PersonRepository personRepository;

    private final HttpClient httpClient = HttpClient.newHttpClient();
    
    
        @SuppressWarnings("unlikely-arg-type")
        public void salvaLivroPorTitulo(String title){
            try {
                String url = "https://gutendex.com/books/?&search=" + title;
    
                HttpRequest request = HttpRequest.newBuilder()
                            .uri(new URI(url))
                            .GET()
                            .build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                ObjectMapper mapper = new ObjectMapper();
                BookResponse bookResponse = mapper.readValue(response.body(), BookResponse.class);

                if (bookResponse.getResults() != null && !bookResponse.getResults().isEmpty()) {
                    Book book = bookResponse.getResults().get(0);
                    Book save = new Book();
                    save.setTitle(book.getTitle());
                    save.setAuthors(book.getAuthors());   
                    save.setLanguages(book.getLanguages());  
                    save.setDownload_count(book.getDownload_count());

                    for (Person author : book.getAuthors()) {
                        if (!personRepository.findByName(author.getName()).contains(author.getName())) { 
                            personRepository.save(author);
                        }
                    }

                    bookRepository.save(save);
                    System.out.println(save);
                } else {
                    System.out.println("Nenhum livro encontrado para o título: " + title);
                }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void listaTodosLivros() {
        System.out.println(bookRepository.listaTodosLivros().stream()
        .map(book -> {
            @SuppressWarnings("unused")
            String authors = book.getAuthors().stream()
                    .map(author -> author.getName())
                    .collect(Collectors.joining(", "));
            return book;
        })
        .collect(Collectors.toList()));
    }

    public void listaLivrosPorIdioma(String idioma) {
        List<Book> livrosFiltrados = bookRepository.listaTodosLivros().stream()
            .filter(book -> book.getLanguages().contains(idioma))
            .collect(Collectors.toList());
    
        if (livrosFiltrados.isEmpty()) {
            System.out.println("Não existem livros nesse idioma no banco de dados");
        } else {
            System.out.println(livrosFiltrados.stream()
                .map(book -> {
                    @SuppressWarnings("unused")
                    String authors = book.getAuthors().stream()
                        .map(author -> author.getName())
                        .collect(Collectors.joining(", "));
                    return book.toString();
                })
                .collect(Collectors.joining("\n")));
        }
    }
    
}