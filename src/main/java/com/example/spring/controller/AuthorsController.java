package com.example.spring.controller;

import com.example.spring.exception.HelloException;
import com.example.spring.model.Author;
import com.example.spring.repository.AuthorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class AuthorsController implements AuthorsNamespace {

    private final AuthorRepository authorRepository;

    public AuthorsController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        return  author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author, UriComponentsBuilder uriComponentsBuilder) {
        Author newAuthor = authorRepository.save(author);
        return ResponseEntity.created
                (uriComponentsBuilder.path("{/authors/authorsId}")
                        .buildAndExpand(newAuthor.getId())
                        .toUri()
                ).build();
    }

    @DeleteMapping("{authorId}")
    public ResponseEntity<Author> delete(@PathVariable Long authorId) {
        authorRepository.deleteById(authorId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/error1")
    public ModelAndView crashMe() {
        throw new HelloException("I crashed!");
    }
}
