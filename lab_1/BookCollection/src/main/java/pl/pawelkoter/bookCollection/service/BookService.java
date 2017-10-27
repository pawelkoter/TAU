package pl.pawelkoter.bookCollection.service;

import pl.pawelkoter.bookCollection.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BookService {

    private BookRepository repository;

    public BookService( BookRepository repository) {
        this.repository = repository;
    }

    public List< Book > searchTitleByRegex( String pattern ) {

        return new ArrayList<>(  );
    }

    public void deleteBooks( List<Book > books) {

        for ( Book book: books ) {
            try {
                repository.delete( book );
            }
            catch ( NoSuchElementException e ) {
                //Ok, should be gone? It is.
            }
        }
    }
}