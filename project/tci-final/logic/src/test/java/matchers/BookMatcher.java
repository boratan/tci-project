package matchers;

import models.Book;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;


public class BookMatcher extends GenericAssert<BookMatcher, Book> {

    public BookMatcher(Book actual) {
        super(BookMatcher.class, actual);
    }
    public static BookMatcher assertThat(Book actual) {
        return new BookMatcher(actual);
    }
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

    public BookMatcher hasIsnb(String arg) {
        isNotNull();
        String errorMessage = String.format(
                "Expected isnb to be <%s> but was <%s>",
                arg, actual.getIsnb());
        Assertions.assertThat(actual.getIsnb())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(arg);
        return this;
    }

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
