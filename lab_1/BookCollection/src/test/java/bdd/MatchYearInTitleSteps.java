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

import static org.assertj.core.api.Assertions.assertThat;

public class MatchYearInTitleSteps {

    private List<Book> booksInRepository;
    private BookRepository bookRepository;
    private BookService bookService;
    private String pattern;
    List<Book> result;

    @Given("^Following books in repository$")
    public void there_are_following_books_in_repository(DataTable books) {

        booksInRepository = books.asList( Book.class );
        assertThat(booksInRepository).isNotNull();

        bookRepository = new InMemoryBookRepository();

        for ( Book book : booksInRepository) {
            bookRepository.create( book );
        }

        bookService = new BookService( bookRepository );
    }

    @Given("^Pattern Matching years$")
    public void pattern_Matching_years() throws Throwable {
        pattern = "\\d{4}|'\\d{2}";
    }

    @When("^I search book by this pattern$")
    public void i_search_book_by_this_pattern() throws Throwable {
        result = bookService.searchTitleByRegex(pattern);
    }

    @Then("^Books with following titles are found$")
    public void books_with_following_titles_are_found(DataTable titles) throws Throwable {
        List<String> bookTitles = titles.asList( String.class );


        assertThat( result ).extracting( "title" )
                            .containsOnlyElementsOf( bookTitles );
    }
}