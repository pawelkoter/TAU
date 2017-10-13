package pl.pawelkoter.bookCollection.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.pawelkoter.bookCollection.domain.Book;

import java.util.List;
import java.util.NoSuchElementException;

public class BookRepositoryTest {
    
    private BookRepository repository = null;
    
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
        
        Assert.assertEquals(book.getTitle(), result.getTitle());
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
    }
    
    @Test
    public void updateTest() {
        Book book = new Book();
        book.setTitle("Ania z zielonego poddasza");
        
        repository.create(book);
        
        book.setTitle("New Title");
        repository.update(book);
        
        Book result = repository.read(book.getId());
        
        Assert.assertEquals(book.getTitle(), result.getTitle());
    }

    @Test(expected = NoSuchElementException.class)
    public void updateThrowsExceptionWhenElementToUpdateWasNotFound()
    {
        repository.update( new Book(  ) );
    }
    
    @Test(expected = NoSuchElementException.class)
    public void deleteThrowsExceptionWhenBookToDeleteWasNotFound() throws Exception {
        repository.delete(new Book());
    }
}
