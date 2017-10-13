package pl.pawelkoter.bookCollection.service;

import pl.pawelkoter.bookCollection.domain.Book;

import java.util.*;

public class InMemoryBookRepository implements BookRepository {

    private Map<Integer, Book> _dbMap;
    private int _currentIndex = 0;

    public InMemoryBookRepository() {
        this._dbMap = new HashMap<Integer, Book>(10);
    }
    @Override
    public void create(Book book) {
        Book entity = new Book(book);
        _dbMap.put(_currentIndex, entity);
        entity.setId( _currentIndex );
        book.setId( _currentIndex );
        _currentIndex++;
    }

    @Override
    public List<Book> read() {
        return new ArrayList< Book >( _dbMap.values() );
    }

    @Override
    public Book read(int bookId) {
        return _dbMap.get(bookId);
    }

    @Override
    public void update(Book book) {
        if( _dbMap.containsKey( book.getId() )) {
            _dbMap.put( book.getId(), book );
        }
        else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void delete(Book book) {
        if( _dbMap.containsKey( book.getId() )) {
            _dbMap.remove(book.getId());
        } else {
            throw new NoSuchElementException();
        }
    }
}
