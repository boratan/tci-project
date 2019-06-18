package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import java.util.*;

import static org.mockito.Mockito.mock;

public class GetAllTest {

    private GetAll ga;

    private String testLog = "";
    @Rule
    public final TestRule watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            testLog += String.format("Test %s started\n", description);
        }

        @Override
        protected void succeeded(Description description) {
            testLog += String.format("Test %s succeeded", description);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            testLog += String.format("Test %s failed", description);
        }

        @Override
        protected void finished(Description description) {
            System.out.println(testLog);
        }
    };

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Rule
    public TestRule globalTimeout = Timeout.seconds(7);

    @Before
    public void setUp() {
        ga = new GetAll();
    }

    /**
     * When the constructor is called,
     * calling getBooks() will return a list of Books.
     */
    @Test
    public void afterConstructionListOfBooksCanBeReturned() {
        List<Book> testBooks = new ArrayList<>();
        Assert.assertEquals(ga.getBooks(), testBooks);
    }

    /**
     * When the constructor is called,
     * calling getMovies() will return a list of Movies.
     */
    @Test
    public void afterConstructionListOfMoviesCanBeReturned() {
        List<Movie> testMovies = new ArrayList<>();
        Assert.assertEquals(ga.getMovies(), testMovies);
    }

    /**
     * When the constructor is called,
     * calling getMusic() will return a list of Music.
     */
    @Test
    public void afterConstructionListOfMusicCanBeReturned() {
        List<Music> testMusic = new ArrayList<>();
        Assert.assertEquals(ga.getMusic(), testMusic);
    }

    /**
     * When the constructor is called with argument empty Set<IModels>,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void splitIntoModelsReturnsIllegalArgumentExceptionSetOfModelsIsEmpty() {
        exception.expect(IllegalArgumentException.class);
        Set<IModel> testSet = Collections.emptySet();
        GetAll getAll = new GetAll(testSet);
    }

    /**
     * When the constructor is called with argument null,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void splitIntoModelsReturnsIllegalArgumentExceptionSetOfModelsIsNull() {
        exception.expect(IllegalArgumentException.class);
        GetAll getAll = new GetAll(null);
    }

    /**
     * When the constructor is called with not empty argument Set<IModels>,
     * a list of Music can be returned upon calling getMusic().
     */
    @Test
    public void afterSplitIntoModelsNotEmptyListOfMusicCanBeReturned() {
        Movie mockMovie1 = mock(Movie.class);
        Music mockMusic = mock(Music.class);
        Book mockBook = mock(Book.class);

        Set<IModel> testModels = new HashSet<>();
        testModels.add(mockMovie1);
        testModels.add(mockBook);
        testModels.add(mockMusic);

        GetAll getAll = new GetAll(testModels);
        Assert.assertNotNull(getAll.getMusic());
    }

    /**
     * When the constructor is called with not empty argument Set<IModels>,
     * a list of Movies can be returned upon calling getMovies().
     */
    @Test
    public void afterSplitIntoModelsNotEmptyListOfMoviesCanBeReturned() {
        Movie mockMovie1 = mock(Movie.class);
        Movie mockMovie2 = mock(Movie.class);
        Book mockBook = mock(Book.class);
        Set<IModel> testModels = new HashSet<>();
        testModels.add(mockMovie1);
        testModels.add(mockBook);
        testModels.add(mockMovie2);

        GetAll getAll = new GetAll(testModels);

        Assert.assertNotNull(getAll.getMovies());
    }

    /**
     * When the constructor is called with not empty argument Set<IModels>,
     * a list of Books can be returned upon calling getBooks().
     */
    @Test
    public void afterSplitIntoModelsNotEmptyListOfBooksCanBeReturned() {
        Movie mockMovie1 = mock(Movie.class);
        Movie mockMovie2 = mock(Movie.class);
        Book mockBook = mock(Book.class);

        Set<IModel> testModels = new HashSet<>();
        testModels.add(mockMovie1);
        testModels.add(mockBook);
        testModels.add(mockMovie2);

        GetAll getAll = new GetAll(testModels);

        Assert.assertNotNull(getAll.getBooks());
    }
}