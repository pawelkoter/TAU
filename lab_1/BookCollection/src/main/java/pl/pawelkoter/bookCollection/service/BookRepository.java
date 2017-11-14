package pl.pawelkoter.bookCollection.service;

import pl.pawelkoter.bookCollection.domain.Book;
import java.util.List;
import java.util.NoSuchElementException;

public interface BookRepository {

    void create( Book book );
    
    List<Book> read();
    
    /**
     * -> obiekt o podanym ID lub null jeśli nie znaleziono
     */
    Book read( int bookId );
    
    /**
     * Generuje wyjątek w sytuacji kiedy taki obiekt nie istnieje w bazie danych
     */
    void update( Book book ) throws NoSuchElementException;
    
    /**
     * Generuje wyjątek w sytuacji kiedy taki obiekt nie istnieje w bazie danych
     */
    void delete( Book book ) throws NoSuchElementException;
}
