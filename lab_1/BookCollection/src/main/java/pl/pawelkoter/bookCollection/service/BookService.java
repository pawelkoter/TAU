package pl.pawelkoter.bookCollection.service;

import pl.pawelkoter.bookCollection.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {

    private BookRepository repository;

    public BookService( BookRepository repository) {
        this.repository = repository;
    }

    public List< Book > searchTitleByRegex( String pattern ) {

        return new ArrayList<>(  );
    }

    public void deleteBooks( List<Book > books) {

    }
}