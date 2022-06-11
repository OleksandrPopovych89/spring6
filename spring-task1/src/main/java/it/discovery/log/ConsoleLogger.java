package it.discovery.log;

public class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("Log to console: " + message);
    }
}
