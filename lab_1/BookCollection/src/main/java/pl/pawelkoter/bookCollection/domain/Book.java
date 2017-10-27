package pl.pawelkoter.bookCollection.domain;

import java.lang.String;

public class Book {
    private int id;
    private String title;

    public Book(){};

    public Book( Book book ) {
        id = book.id;
        title = book.title;
    }

    public int getId() {
        return id;
    }

    public Book setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }
}
