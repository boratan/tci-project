package matchers;

import models.Music;
import org.fest.assertions.Assertions;
import org.fest.assertions.GenericAssert;

public class MusicMatcher extends GenericAssert<MusicMatcher, Music> {

    public MusicMatcher(Music actual) {
        super(MusicMatcher.class, actual);
    }
    public static MusicMatcher assertThat(Music actual) {
        return new MusicMatcher(actual);
    }
    public MusicMatcher hasName(String name) {
        isNotNull();
        String errorMessage = String.format(
                "Expected name to be <%s> but was <%s>",
                name, actual.getName());
        Assertions.assertThat(actual.getName())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(name);
        return this;
    }

    public MusicMatcher hasArtist(String artist) {
        isNotNull();
        String errorMessage = String.format(
                "Expected artist to be <%s> but was <%s>",
                artist, actual.getArtist());
        Assertions.assertThat(actual.getArtist())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(artist);
        return this;
    }

    public MusicMatcher hasFormat(String format) {
        isNotNull();
        String errorMessage = String.format(
                "Expected format to be <%s> but was <%s>",
                format, actual.getFormat());
        Assertions.assertThat(actual.getFormat())
                .overridingErrorMessage(errorMessage)
                .isEqualTo(format);
        return this;
    }

    public MusicMatcher hasGenre(String arg) {
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
