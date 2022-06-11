package it.discovery.config;

import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XmlBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.MainBookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration(proxyBeanMethods = false)
@PropertySource("application.properties")
public class AppConfiguration {
    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Qualifier("db")
    @Profile("prod")
//    @Primary
    public BookRepository dbRepository() {
        return new DBBookRepository();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    @Qualifier("xml")
    @Profile("dev")
    public BookRepository xmlRepository(Environment environment) {
        return new XmlBookRepository(environment.getRequiredProperty("xml.file"));
    }

    @Bean
    public BookService bookService(BookRepository bookRepository) {
        return new MainBookService(bookRepository);
    }
}
