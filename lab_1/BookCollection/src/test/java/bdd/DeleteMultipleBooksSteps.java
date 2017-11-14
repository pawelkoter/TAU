package bdd;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.pawelkoter.bookCollection.domain.Book;
import pl.pawelkoter.bookCollection.service.BookRepository;
import pl.pawelkoter.bookCollection.service.BookService;
import pl.pawelkoter.bookCollection.service.InMemoryBookRepository;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteMultipleBooksSteps {
    private List<Book> booksInRepository;
    private BookRepository bookRepository;
    private BookService bookService;

    @Given("^There are following books in repository$")
    public void there_are_following_books_in_repository(DataTable books) {

        booksInRepository = books.asList( Book.class );
        assertThat(booksInRepository).isNotNull();

        bookRepository = new InMemoryBookRepository();

        for ( Book book : booksInRepository) {
            bookRepository.create( book );
        }

        bookService = new BookService( bookRepository );
    }

    @When("^I delete$")
    public void i_delete(DataTable books) throws Throwable {

        List<Book> booksToDelete = books.asList( Book.class );

        for (Book book : booksToDelete) {
            for (Book savedBook : bookRepository.read()) {
                if ( Objects.equals( book.getTitle(), savedBook.getTitle() ) ) {
                    book.setId( savedBook.getId() );
                }
            }
        }

        assertThat(booksToDelete).isNotNull();

        bookService.deleteBooks( booksToDelete );

    }

    @Then("^Only books with following titles are left$")
    public void only_books_with_following_titles_are_left(DataTable titles) throws Throwable {
        List<String> bookTitles = titles.asList( String.class );


        assertThat( bookRepository.read() ).extracting( "title" )
                                           .containsOnlyElementsOf( bookTitles );
    }
}
