package matchers;

import models.Movie;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

public class MovieMatcher extends GenericAssert<MovieMatcher, Movie> {

    public MovieMatcher(Movie actual) {
        super(MovieMatcher.class, actual);
    }
    public static MovieMatcher assertThat(Movie actual) {
        return new MovieMatcher(actual);
    }
    public MovieMatcher hasName(String name) {
        isNotNull();
        String errorMessage = String.format(
                "Expected name to be <%s> but was <%s>",
                name, actual.getName());
        Assertions.assertThat(actual.getName())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(name);
        return this;
    }

    public MovieMatcher hasDirector(String director) {
        isNotNull();
        String errorMessage = String.format(
                "Expected director to be <%s> but was <%s>",
                director, actual.getDirector());
        Assertions.assertThat(actual.getDirector())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(director);
        return this;
    }

    public MovieMatcher hasFormat(String format) {
        isNotNull();
        String errorMessage = String.format(
                "Expected format to be <%s> but was <%s>",
                format, actual.getFormat());
        Assertions.assertThat(actual.getFormat())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(format);
        return this;
    }

    public MovieMatcher hasGenre(String arg) {
        isNotNull();
        String errorMessage = String.format(
                "Expected genre to be <%s> but was <%s>",
                arg, actual.getGenre());
        Assertions.assertThat(actual.getGenre())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(arg);
        return this;
    }
}
