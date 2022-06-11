package it.discovery.log;

public class FileLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("Log to file: " + message);
    }
}
