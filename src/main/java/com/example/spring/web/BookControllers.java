package com.example.spring.web;

import com.example.spring.model.Author;
import com.example.spring.model.Book;
import com.example.spring.repository.AuthorRepository;
import com.example.spring.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class BookControllers implements AuthorsNamespace {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookControllers(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/{authorId}/books")
    public ResponseEntity<List<Book>> getAuthorBooks(@PathVariable Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        return author
                .map(Author::getBookList)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/{authorId}/books/{bookId}")
    public ResponseEntity<Book> getAuthorBookById(@PathVariable Long authorId, @PathVariable Long bookId) {
        Optional<Book> book = bookRepository.findById(bookId);
        return book
                .filter(b -> b.getAuthor().getId() == authorId)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }
}



















