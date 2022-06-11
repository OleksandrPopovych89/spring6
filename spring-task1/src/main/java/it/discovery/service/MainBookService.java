package it.discovery.service;

import it.discovery.model.Book;
import it.discovery.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
@Service

public class MainBookService implements BookService {
    private final BookRepository repository;

    private boolean cachingEnabled;

    private final Map<Integer, Book> bookCache = new ConcurrentHashMap<>();

    @Inject
    private List<BookRepository> repositories;

    public MainBookService(/*@Qualifier("xml")*/ BookRepository repository) {
        this.repository = repository;
        System.out.println("Using  repository " + repository.getClass());
    }

    @PostConstruct
    public void init() {
        System.out.println("Found Repositories " + repositories);
    }

    @Override
    public void saveBook(Book book) {
        repository.saveBook(book);

        if (cachingEnabled) {
            bookCache.put(book.getId(), book);
        }
    }

    @Override
    public Book findBookById(int id) {
        if (cachingEnabled && bookCache.containsKey(id)) {
            return bookCache.get(id);
        }


        return repository.findBookById(id);
    }

    @Override
    public List<Book> findBooks() {
        return repository.findBooks();
    }
}
