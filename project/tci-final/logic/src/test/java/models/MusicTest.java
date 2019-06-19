package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static matchers.IsValidYearMatcher.isValidYearMatcher;
import static org.hamcrest.MatcherAssert.assertThat;
import static matchers.MusicMatcher.assertThat;

/**
 * Author: M. Andreeva
 */
public class MusicTest {

    private Music music;

    /**
     * setup
     */
    @Before
    public void setUp() {
        music = new Music("test song", "test genre", "test format", 1999, "test artist" );
    }

    /**
     * The test invokes SUT method getName. Expected is not null String.
     */
    @Test
    public void musicNameIsNotNullWhenConstructorValueIsNotNull(){
        //arrange is in the setUp
        //act
        String name = music.getName();

        //assert
        assertThat(music).hasName(name);
    }

    /**
     * The test invokes SUT method getGenre. Expected is not null String.
     */
    @Test
    public void musicGenreIsNotNullWhenConstructorValueIsNotNull(){
        //arrange is in the setUp
        //act
        String genre = music.getGenre();

        //assert
        assertThat(music).hasGenre(genre);
    }

    /**
     * The test invokes SUT method getFormat. Expected is not null String.
     */
    @Test
    public void musicFormatIsNotNullWhenConstructorValueIsNotNull(){
        //arrange is in the setUp
        //act
        String format = music.getFormat();

        //assert
        assertThat(music).hasFormat(format);
    }

    /**
     * The test invokes SUT method getYear. Expected is not null Integer.
     */
    @Test
    public void musicYearIsNotNullWhenConstructorValueIsNotNull(){
        //arrange is in the setUp
        //act
        Integer year = music.getYear();

        //assert
        Assert.assertNotNull(year);
    }

    /**
     * The test invokes SUT method getArtist. Expected is not null String.
     */
    @Test
    public void musicArtistIsNotNullWhenConstructorValueIsNotNull(){
        //arrange is in the setUp
        //act
        String artist = music.getArtist();

        //assert
        assertThat(music).hasArtist(artist);
    }

    /**
     * The test invokes SUT method setName with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void musicNameThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        music.setName(null);

        //assert is the expected exception
    }

    /**
     * The test invokes SUT method setGenre with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void musicGenreThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        music.setGenre(null);

        //assert is the expected exception
    }

    /**
     * The test invokes SUT method setFormat with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void musicFormatThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        music.setFormat(null);

        //assert is the expected exception
    }

    /**
     * The test invokes SUT method setYear with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void musicYearThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        music.setYear(null);

        //assert is the expected exception
    }

    /**
     * The test invokes SUT method setArtist with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void musicArtistThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        music.setArtist(null);

        //assert is the expected exception
    }

    /**
     * The test sets the Name property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void musicNameIsSetAsDesired(){
        //act
        music.setName("Music Name");
        String actual = music.getName();

        //assert
        Assert.assertEquals("Music Name", actual);
    }

    /**
     * The test sets the Genre property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void musicGenreIsSetAsDesired(){
        //act
        music.setGenre("Music Genre");
        String actual = music.getGenre();

        //assert
        Assert.assertEquals("Music Genre", actual);
    }

    /**
     * The test sets the Format property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void musicFormatIsSetAsDesired(){
        //act
        music.setFormat("Music format");
        String actual = music.getFormat();

        //assert
        Assert.assertEquals("Music format", actual);
    }

    /**
     * The test sets the Year property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void musicYearIsSetAsDesired(){
        //act
        music.setYear(1999);
        Integer actual = music.getYear();

        //assert
        Assert.assertEquals(Integer.valueOf(1999), actual);
    }

    /**
     * The test sets the Year property of SUT and then checks if the one we get is a valid year.
     */
    @Test
    public void movieYearIsValidYear(){
        //act
        music.setYear(1999);
        Integer actual = music.getYear();

        //assert
        assertThat(actual, isValidYearMatcher());
    }

    /**
     * The test sets the Artist property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void musicArtistIsSetAsDesired(){
        //act
        music.setArtist("Music artist");
        String actual = music.getArtist();

        //assert
        Assert.assertEquals("Music artist", actual);
    }
}