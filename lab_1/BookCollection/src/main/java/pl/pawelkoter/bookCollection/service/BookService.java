package pl.pawelkoter.bookCollection.service;

import pl.pawelkoter.bookCollection.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class BookService {

    private BookRepository repository;

    public BookService( BookRepository repository) {
        this.repository = repository;
    }

    public List< Book > searchTitleByRegex( String regex ) {
        Pattern pattern = Pattern.compile(regex);

        List<Book> allBooks = repository.read();

        List<Book> result = new ArrayList<>();

        for ( Book book : allBooks ) {
            if ( pattern.matcher( book.getTitle() ).find() ) {
                result.add( book );
            }
        }
        return result;
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