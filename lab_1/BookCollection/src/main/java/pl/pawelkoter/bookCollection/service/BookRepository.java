package pl.pawelkoter.bookCollection.service;

import pl.pawelkoter.bookCollection.domain.Book;
import java.util.List;

public interface BookRepository {
    
    /**
     * 
     */ 
    public void create(Book book);
    
    /**
     * 
     */ 
    public List<Book> read();
    
    /**
     * -> obiekt o podanym ID lub null jeśli nie znaleziono
     */ 
    public Book read(int bookId);
    
    /**
     * Generuje wyjątek w sytuacji kiedy taki obiekt nie istnieje w bazie danych
     */ 
    public void update(Book book);
    
    /**
     * Generuje wyjątek w sytuacji kiedy taki obiekt nie istnieje w bazie danych
     */ 
    public void delete(Book book) ; 
}
