package com.example.spring;

import com.example.spring.model.Author;
import com.example.spring.model.Book;
import com.example.spring.repository.AuthorRepository;
import com.example.spring.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestApplicationInit implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    ObjectMapper om;

    public RestApplicationInit(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) {

        innitJonkov();
        innitNikolaiHaitov();
        innitDimiturTalev();
        innitElinPelin();
        innitVazov();

    }

    private void innitVazov() {
        initAuthor(
                "Ivan Vazov",
                "Pripotqsht i Gysla",
                "Pod Igoto",
                "Tugite na Bulgaria"
        );
    }

    private void innitElinPelin() {
        initAuthor(
                "Elin Pelin",
                "Pijo i Penda",
                "Qn Bibiqn na lunata",
                "pod manastirskata loza"
        );
    }

    private void innitDimiturTalev() {
        initAuthor(
                "Dimitur Talev",
                "Tytyn"
        );
    }

    private void innitNikolaiHaitov() {
        initAuthor(
                "Nikolai Haitov",
                "Divi Razkazi"
        );

    }

    private void innitJonkov() {
        initAuthor(
                "Yordan Yovkov",
                "Staroplaninski legendi",
                "Chiflikut krai granicata"
        );
    }

    private void initAuthor(String authorName, String... books) {
        Author author = new Author();
        author.setName(authorName);

        author = authorRepository.save(author);

        List<Book> bookList = new ArrayList<>();
        for (String book: books) {
            Book aBook = new Book();
            aBook.setAuthor(author);
            aBook.setTitle(book);
            bookList.add(aBook);
        }
        bookRepository.saveAll(bookList);
    }
}
