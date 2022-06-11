package it.discovery.config;

import it.discovery.log.ConsoleLogger;
import it.discovery.log.FileLogger;
import it.discovery.log.Logger;
import it.discovery.repository.BookRepository;
import it.discovery.repository.DBBookRepository;
import it.discovery.repository.XmlBookRepository;
import it.discovery.service.BookService;
import it.discovery.service.MainBookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.util.List;

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
    public BookService bookService(BookRepository bookRepository, List<Logger> loggers) {
        return new MainBookService(bookRepository, loggers);
    }

    @Configuration
    public static class LogConfiguration {
        @Bean
        public Logger fileLogger() {
            return new FileLogger();
        }

        @Bean
        @Order(Ordered.HIGHEST_PRECEDENCE)
        public Logger consoleLogger() {
            return new ConsoleLogger();
        }
    }
}
