package pl.pawelkoter.bookCollection.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.pawelkoter.bookCollection.domain.Book;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.times;

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
        List<Book> books = new ArrayList<>(
                Arrays.asList(
                        new Book().setTitle( "Metro 2033" ),
                        new Book().setTitle( "Witajcie w Rosji" ),
                        new Book().setTitle( "Apokalypsis '89" ),
                        new Book().setTitle( "Haszyszopenki" )
                )
        );
        given(bookRepositoryMock.read()).willReturn( books );
        String pattern = "\\d{4}|'\\d{2}";

        //When
        List<Book> result = bookService.searchTitleByRegex(pattern);

        //Then
        assertThat( result ).isNotNull();
        assertThat( result ).extracting( "title" )
                            .containsOnly( "Metro 2033",  "Apokalypsis '89");
    }

    @Test
    public void deleteBooksTest() {
        //Given
        Book delete1 = new Book();
        Book delete2 = new Book();
        List<Book> toDelete = new ArrayList<>(
                Arrays.asList(
                        delete1,
                        delete2
                )
        );

        //When
        bookService.deleteBooks( toDelete );

        //Then
        then( bookRepositoryMock ).should(times( 1 )).delete( delete1 );
        then( bookRepositoryMock ).should(times( 1 )).delete( delete2 );
    }

    @Test
    public void deleteDoesNotThrowException_WhenRepositoryFailsToDelete() {
        //Given
        willThrow( NoSuchElementException.class ).given( bookRepositoryMock ).delete( any( Book.class ) );

        //When
        bookService.deleteBooks( Arrays.asList( new Book() ) );

        //Then
        //no exception is thrown
    }
}