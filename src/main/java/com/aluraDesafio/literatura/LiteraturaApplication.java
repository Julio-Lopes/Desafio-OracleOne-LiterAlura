package com.aluraDesafio.literatura;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aluraDesafio.literatura.Controller.BookController;
import com.aluraDesafio.literatura.Service.PersonService;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	@Autowired
    private BookController bookService;

    @Autowired
    private PersonService personService;

    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        while (true) {
            System.out.println("Escolha o numero de sua opção:");
            System.out.println("1- Buscar livro pelo titulo");
            System.out.println("2- Listar livros registrados");
            System.out.println("3- Listar autores registrados");
            System.out.println("4- Listar autores vivos em um determinado ano");
            System.out.println("5- Listar livros em um determinado idioma");
            System.out.println("6- Buscar por nome do autor");
            System.out.println("0- Sair");

            int opcao = scanner.nextInt();

            if(opcao == 0) break;

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do livro: ");
                    scanner.nextLine();
                    String livro = scanner.nextLine();
                    bookService.salvaLivroPorTitulo(livro);
                    break;
                case 2:
                    bookService.listaTodosLivros();
                    break;
                case 3:
                    personService.listaTodosAutores();
                    break;
                case 4:
                    System.out.println("Digite o ano: ");
                    scanner.nextLine();
                    int ano = scanner.nextInt();
                    personService.listaAutoresVivoNoAno(ano);
                    break;
                case 5:
                    System.out.println("Insira o idioma para realizar a busca:");
                    System.out.println("es- Espanhol");
                    System.out.println("en- Inglês");
                    System.out.println("fr- Francês");
                    System.out.println("pt- Português");
                    scanner.nextLine();
                    String idioma = scanner.nextLine();
                    if(idioma == "es" || idioma == "en" || idioma == "fr" || idioma == "pt") bookService.listaLivrosPorIdioma(idioma);
                    else System.out.println("Não existem livros nesse idioma no banco de dados");
                    break;
                case 6:
                    System.out.println("Digite o nome do autor:");
                    scanner.nextLine();
                    String nome = scanner.nextLine();
                    personService.buscaPorNomeAutor(nome);
                    break;
                default:
                    break;
            }
        }
        scanner.close();
    }

}