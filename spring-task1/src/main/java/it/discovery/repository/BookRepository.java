package it.discovery.repository;

import it.discovery.model.Book;

import java.util.List;

public interface BookRepository {
    void saveBook(Book book);

    Book findBookById(int id);

    List<Book> findBooks();
}
