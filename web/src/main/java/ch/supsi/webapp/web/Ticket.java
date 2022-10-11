package ch.supsi.webapp.web;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    private static final AtomicInteger counter = new AtomicInteger(0);
    private final int id;
    private String title;
    private String description;
    private String author;

    public Ticket(String title, String description, String author) {
        this();
        this.title = title;
        this.description = description;
        this.author = author;
    }

    public Ticket() {
        this.id = counter.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
