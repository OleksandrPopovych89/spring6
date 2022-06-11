package it.discovery.loader;

import it.discovery.model.Book;
import it.discovery.repository.DBBookRepository;
import it.discovery.service.BookService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class SpringStarter {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext("it.discovery")) {

            BookService service = context.getBean(BookService.class); //TODO Load from context

            Book book = new Book();
            book.setName("Introduction into Spring");
            book.setPages(100);
            book.setYear(2016);
            service.saveBook(book);

            var books = service.findBooks();
            System.out.println(books);
            System.out.println("Total bean count " + context.getBeanDefinitionCount());
            System.out.println("Bean identifiers " + Arrays.toString(context.getBeanDefinitionNames()));
        }

    }

}
