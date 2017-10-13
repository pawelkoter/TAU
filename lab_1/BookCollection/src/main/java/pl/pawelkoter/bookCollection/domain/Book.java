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

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
