package it.discovery.loader;

import it.discovery.model.Book;
import it.discovery.repository.DBBookRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class SpringStarter {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext("it.discovery")) {

            DBBookRepository repository = context.getBean(DBBookRepository.class); //TODO Load from context

            Book book = new Book();
            book.setName("Introduction into Spring");
            book.setPages(100);
            book.setYear(2016);
            repository.saveBook(book);

            var books = repository.findBooks();
            System.out.println(books);
            System.out.println("Total bean count " + context.getBeanDefinitionCount());
            System.out.println("Bean identifiers " + Arrays.toString(context.getBeanDefinitionNames()));
        }

    }

}
