package pl.pawelkoter.bookCollection.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import pl.pawelkoter.bookCollection.domain.Book;

public class InMemoryBookRepository implements BookRepository {

    private List<Book> _db = new ArrayList<Book>();
    @Override
    public void create(Book book) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Book> read() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Book read(int bookId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Book book) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Book book) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
