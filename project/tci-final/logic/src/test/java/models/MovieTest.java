package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static matchers.IsValidYearMatcher.isValidYearMatcher;
import static org.hamcrest.MatcherAssert.assertThat;
import static matchers.MovieMatcher.assertThat;

/**
 * Author: M. Andreeva
 */
public class MovieTest {

    private Movie movie;

    /**
     * setup
     */
    @Before
    public void setUp() {
        List<String> writers = new ArrayList<>();
        writers.add("George Clayton Johnson");
        writers.add("Jack Golden Russell");

        List<String> stars = new ArrayList<>();
        stars.add("George Clooney");
        stars.add("Brad Pitt");
        stars.add("Julia Roberts");
        movie = new Movie("Ocean 11", "Action", "DVD", 2001, "Steven Soderbergh", writers, stars );
    }

    /**
     * The test invokes SUT method getName. Expected is not null String.
     */
    @Test
    public void movieNameIsNotNullWhenConstructorIsInvoked(){
        //arrange is in the setUp
        //act
        String name = movie.getName();

        //assert
        assertThat(movie).hasName(name);
    }

    /**
     * The test invokes SUT method getGenre. Expected is not null String.
     */
    @Test
    public void movieGenreIsNotNullWhenConstructorIsInvoked(){
        //arrange is in the setUp
        //act
        String genre = movie.getGenre();

        //assert
        assertThat(movie).hasGenre(genre);
    }

    /**
     * The test invokes SUT method getFormat. Expected is not null String.
     */
    @Test
    public void movieFormatIsNotNullWhenConstructorIsInvoked(){
        //arrange is in the setUp
        //act
        String format = movie.getFormat();

        //assert
        assertThat(movie).hasFormat(format);
    }

    /**
     * The test invokes SUT method getYear. Expected is not null Integer.
     */
    @Test
    public void movieYearIsNotNullWhenConstructorIsInvoked(){
        //arrange is in the setUp
        //act
        Integer year = movie.getYear();

        //assert
        Assert.assertNotNull(year);
    }

    /**
     * The test invokes SUT method getDirector. Expected is not null String.
     */
    @Test
    public void movieDirectorIsNotNullWhenConstructorIsInvoked(){
        //arrange is in the setUp
        //act
        String director = movie.getDirector();

        //assert
        assertThat(movie).hasDirector(director);
    }

    /**
     * The test invokes SUT method getWriters. Expected is not null List of Strings.
     */
    @Test
    public void movieWritersAreNotNullWhenConstructorIsInvoked(){
        //arrange is in the setUp
        //act
        List<String> writers = movie.getWriters();

        //assert
        Assert.assertNotNull(writers);
    }

    /**
     * The test invokes SUT method getStars. Expected is not null List of Strings.
     */
    @Test
    public void movieStarsAreNotNullWhenConstructorIsInvoked(){
        //arrange is in the setUp
        //act
        List<String> stars = movie.getStars();

        //assert
        Assert.assertNotNull(stars);
    }

    /**
     * The test invokes SUT method setName with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void movieNameThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        movie.setName(null);

        //assert is the expected exception
    }

    /**
     * The test invokes SUT method setGenre with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void movieGenreThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        movie.setGenre(null);

        //assert is the expected exception
    }

    /**
     * The test invokes SUT method setFormat with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void movieFormatThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        movie.setFormat(null);

        //assert is the expected exception
    }

    /**
     * The test invokes SUT method setYear with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void movieYearThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        movie.setYear(null);

        //assert is the expected exception
    }

    /**
     * The test invokes SUT method setDirector with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void movieDirectorThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        movie.setDirector(null);

        //assert is the expected exception
    }

    /**
     * The test invokes SUT method setWriters with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void movieWritersThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        movie.setWriters(null);

        //assert is the expected exception
    }

    /**
     * The test invokes SUT method setStars with null parameter.
     */
    @Test(expected = IllegalArgumentException.class)
    public void movieStarsThrowsExceptionIfTheGivenValueIsNull(){
        //arrange is in the setUp
        //act
        movie.setStars(null);

        //assert is the expected exception
    }

    /**
     * The test sets the Name property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void movieNameIsSetAsDesired(){
        //act
        movie.setName("Test Name");
        String actual = movie.getName();

        //assert
        Assert.assertEquals("Test Name", actual);
    }

    /**
     * The test sets the Genre property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void movieGenreIsSetAsDesired(){
        //act
        movie.setGenre("Test Genre");
        String actual = movie.getGenre();

        //assert
        Assert.assertEquals("Test Genre", actual);
    }

    /**
     * The test sets the Format property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void movieFormatIsSetAsDesired(){
        //act
        movie.setFormat("Test format");
        String actual = movie.getFormat();

        //assert
        Assert.assertEquals("Test format", actual);
    }

    /**
     * The test sets the Year property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void movieYearIsSetAsDesired(){
        //act
        movie.setYear(1999);
        Integer actual = movie.getYear();

        //assert
        Assert.assertEquals(Integer.valueOf(1999), actual);
    }

    /**
     * The test sets the Year property of SUT and then checks if the one we get is a valid year.
     */
    @Test
    public void movieYearIsValidYear(){
        //act
        movie.setYear(1999);
        Integer actual = movie.getYear();

        //assert
        assertThat(actual, isValidYearMatcher());
    }

    /**
     * The test sets the Director property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void movieDirectorIsSetAsDesired(){
        //act
        movie.setDirector("Test Director");
        String actual = movie.getDirector();

        //assert
        Assert.assertEquals("Test Director", actual);
    }

    /**
     * The test sets the Writers property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void movieWritersAreSetAsDesired(){
        //arrange
        List<String> writers = new ArrayList<>();
        writers.add("Test");

        //act
        movie.setWriters(writers);
        List<String> actual = movie.getWriters();

        //assert
        Assert.assertEquals(writers, actual);
    }

    /**
     * The test sets the Stars property of SUT and then checks if the one we get is the same as the one we set.
     */
    @Test
    public void movieStarsAreSetAsDesired(){
        //arrange
        List<String> stars = new ArrayList<>();
        stars.add("Test");

        //act
        movie.setStars(stars);
        List<String> actual = movie.getStars();

        //assert
        Assert.assertEquals(stars, actual);
    }
}