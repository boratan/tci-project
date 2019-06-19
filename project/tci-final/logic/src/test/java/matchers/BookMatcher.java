package matchers;

import models.Book;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

/**
 * Matcher for book
 */
public class BookMatcher extends GenericAssert<BookMatcher, Book> {

    /**
     * constructor
     */
    public BookMatcher(Book actual) {
        super(BookMatcher.class, actual);
    }
    /**
     * assertor
     */
    public static BookMatcher assertThat(Book actual) {
        return new BookMatcher(actual);
    }
    /**
     * assert
     */
    public BookMatcher hasName(String name) {
        isNotNull();
        String errorMessage = String.format(
                "Expected name to be <%s> but was <%s>",
                name, actual.getName());
        Assertions.assertThat(actual.getName())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(name);
        return this;
    }
    /**
     * assert
     */
    public BookMatcher hasAuthors(String arg) {
        isNotNull();
        String errorMessage = String.format(
                "Expected author to be <%s> but was <%s>",
                arg, actual.getAuthors());
        Assertions.assertThat(actual.getAuthors())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(arg);
        return this;
    }
    /**
     * assert
     */
    public BookMatcher hasFormat(String format) {
        isNotNull();
        String errorMessage = String.format(
                "Expected format to be <%s> but was <%s>",
                format, actual.getFormat());
        Assertions.assertThat(actual.getFormat())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(format);
        return this;
    }
    /**
     * assert
     */
    public BookMatcher getIsbn(String arg) {
        isNotNull();
        String errorMessage = String.format(
                "Expected isnb to be <%s> but was <%s>",
                arg, actual.getIsbn());
        Assertions.assertThat(actual.getIsbn())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(arg);
        return this;
    }
    /**
     * assert
     */
    public BookMatcher hasPublisher(String arg) {
        isNotNull();
        String errorMessage = String.format(
                "Expected publisher to be <%s> but was <%s>",
                arg, actual.getPublisher());
        Assertions.assertThat(actual.getPublisher())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(arg);
        return this;
    }
}
