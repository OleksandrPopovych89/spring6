package it.discovery.service;

import it.discovery.model.Book;

import java.util.List;

public interface BookService {
    void saveBook(Book book);

    Book findBookById(int id);

    List<Book> findBooks();

    boolean isCachingEnabled();
}
