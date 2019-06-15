package models;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GetAllTest {

    private GetAll ga = new GetAll();

    @Test
    public void afterConstructionListOfBooksCanBeReturned() {
        //GetAll ga = new GetAll();
        List<Book> testBooks = new ArrayList<>();
        Assert.assertEquals(ga.getBooks(), testBooks);
    }

    @Test
    public void afterConstructionListOfMoviesCanBeReturned() {
        //GetAll ga = new GetAll();
        List<Movie> testMovies = new ArrayList<>();
        Assert.assertEquals(ga.getMovies(), testMovies);
    }

    @Test
    public void afterConstructionListOfMusicCanBeReturned() {
        //GetAll ga = new GetAll();
        List<Music> testMusic = new ArrayList<>();
        Assert.assertEquals(ga.getMusic(), testMusic);
    }

    @Test (expected = IllegalArgumentException.class)
    public void splitIntoModelsReturnsIllegalArgumentExceptionSetOfModelsIsEmpty() {
        Set<IModel> testSet = Collections.emptySet();
        GetAll getAll = new GetAll(testSet);
    }

    @Test
    public void afterSplitIntoModelsNotEmptyListOfMusicCanBeReturned() {
        Movie mockMovie1 = mock(Movie.class);
        Music mockMusic = mock(Music.class);
        Book mockBook = mock(Book.class);

        List<Music> exectedList = new ArrayList<>();
        exectedList.add(mockMusic);

        Set<IModel> testModels = new HashSet<>();
        testModels.add(mockMovie1);
        testModels.add(mockBook);
        testModels.add(mockMusic);

        GetAll getAll = new GetAll(testModels);

        Assert.assertEquals(exectedList, getAll.getMusic());
    }

    @Test
    public void afterSplitIntoModelsNotEmptyListOfMoviesCanBeReturned() {
        Movie mockMovie1 = mock(Movie.class);
        Movie mockMovie2 = mock(Movie.class);
        Book mockBook = mock(Book.class);

        List<Movie> exectedList = new ArrayList<>();
        exectedList.add(mockMovie1);
        exectedList.add(mockMovie2);

        Set<IModel> testModels = new HashSet<>();
        testModels.add(mockMovie1);
        testModels.add(mockBook);
        testModels.add(mockMovie2);

        GetAll getAll = new GetAll(testModels);

        Assert.assertEquals(exectedList, getAll.getMovies());
    }

    @Test
    public void afterSplitIntoModelsNotEmptyListOfBooksCanBeReturned() {
        Movie mockMovie1 = mock(Movie.class);
        Movie mockMovie2 = mock(Movie.class);
        Book mockBook = mock(Book.class);

        List<Book> exectedList = new ArrayList<>();
        exectedList.add(mockBook);

        Set<IModel> testModels = new HashSet<>();
        testModels.add(mockMovie1);
        testModels.add(mockBook);
        testModels.add(mockMovie2);

        GetAll getAll = new GetAll(testModels);

        Assert.assertEquals(exectedList, getAll.getBooks());
    }
}