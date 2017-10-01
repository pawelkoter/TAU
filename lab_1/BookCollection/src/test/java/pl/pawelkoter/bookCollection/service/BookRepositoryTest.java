package pl.pawelkoter.bookCollection.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import pl.pawelkoter.bookCollection.domain.Book;

public class BookRepositoryTest {
    
    BookRepository repository = null;
    
    @Before
    public void SetUp() {
        repository = new InMemoryBookRepository();
    }
       
    @Test
    public void readByIdReturnsNullWhenBookIsNotFound() {
        Book result = repository.read(12);
        
        Assert.assertNull(result);
    }
    
    @Test
    public void readByIdTest() {
        Book book = new Book();
        book.setTitle("Ania z zielonego poddasza");
        
        repository.create(book);
        
        Book result = repository.read(book.getId());
        
        Assert.assertEquals(book, result);
    }
        
    @Test
    public void readAllReturnsEmptyListWhenThereAreNoBooksSaved() {
        List<Book> result = repository.read();
        
        Assert.assertTrue(result.isEmpty());
    }
    
    @Test
    public void readAllReturnsAllBooks() {
        Book book_1 = new Book();
        book_1.setTitle("Title 1");
        
        Book book_2 = new Book();
        book_2.setTitle("Title 2");
        
        Book book_3 = new Book();
        book_3.setTitle("Title 3");
        
        repository.create(book_1);
        repository.create(book_2);
        repository.create(book_3);
        
        List<Book> result = repository.read();
        
        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.contains(book_1));
        Assert.assertTrue(result.contains(book_2));
        Assert.assertTrue(result.contains(book_3));
    }
    
    @Test
    public void updateTest() {
        Book book = new Book();
        book.setTitle("Ania z zielonego poddasza");
        
        repository.create(book);
        
        book.setTitle("New Title");
        repository.update(book);
        
        Book result = repository.read(book.getId());
        
        Assert.assertEquals(book, result);
    }
    
    @Test(expected = NoSuchElementException.class)
    public void deleteThrowsExceptionWhenBookToDeleteWasNotFound() throws Exception {
        repository.delete(new Book());
    }
}
