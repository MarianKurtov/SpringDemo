package com.example.spring.web;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AuthorsNamespace.URI_AUTHORS)
public interface AuthorsNamespace {
    String URI_AUTHORS="/authors";
}
