package pl.pawelkoter.bookCollection.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.pawelkoter.bookCollection.domain.Book;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class BookServiceTest {
    private BookService bookService;
    private BookRepository bookRepositoryMock;

    @Before
    public void setUp() {
        bookRepositoryMock = Mockito.mock( BookRepository.class );
        bookService = new BookService( bookRepositoryMock );
    }

    @Test
    public void searchByRegexTest() {
        //Given
        given(bookRepositoryMock.read()).willReturn( new ArrayList< Book >() );
        String pattern = "";

        //When
        List<Book> result = bookService.searchTitleByRegex(pattern);

        //Then
        assertThat(result).isNotNull();
    }

    @Test
    public void deleteBooksTest() {
        bookService.deleteBooks( new LinkedList< Book >(  ) );
    }
}