package mappers;

import helper.ScrapedDataDoesNotContainIModelException;
import models.Book;
import models.IModel;
import models.Movie;
import models.Music;
import org.jsoup.nodes.Element;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: M. Andreeva
 */
public class ModelMapperTest {

    private ModelMapper mapper;
    private Element bookElement;
    private Element movieElement;
    private Element musicElement;

    /**
     * setup
     */
    @Before
    public void setUp() {
        mapper = new ModelMapper();

        arrangeBook();
        arrangeMovie();
        arrangeMusic();
    }

    /**
     * Populated the bookElement with proper html structure.
     */
    private void arrangeBook() {
        bookElement = new Element("div");
        bookElement.addClass("media-details");
        bookElement.appendElement("h1").text("name");

        Element tbody = bookElement.appendElement("table").appendElement("tbody");
        Element row = tbody.appendElement("tr");
        row.appendElement("th").text("Category");
        row.appendElement("td").text("Books");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Genre");
        row.appendElement("td").text("genre");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Format");
        row.appendElement("td").text("format");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Year");
        row.appendElement("td").text("1925");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Authors");
        row.appendElement("td").text("authors");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Publisher");
        row.appendElement("td").text("publisher");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("ISBN");
        row.appendElement("td").text("978-0132350884");
    }
    /**
     * Populated the movieElement with proper html structure.
     */
    private void arrangeMovie() {
        movieElement = new Element("div");
        movieElement.addClass("media-details");
        movieElement.appendElement("h1").text("name");

        Element tbody = movieElement.appendElement("table").appendElement("tbody");
        Element row = tbody.appendElement("tr");
        row.appendElement("th").text("Category");
        row.appendElement("td").text("Movies");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Genre");
        row.appendElement("td").text("genre");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Format");
        row.appendElement("td").text("format");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Year");
        row.appendElement("td").text("1925");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Director");
        row.appendElement("td").text("director");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Writers");
        row.appendElement("td").text("writer1, writer2");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Stars");
        row.appendElement("td").text("Star1, Star2");
    }
    /**
     * Populated the musicElement with proper html structure.
     */
    private void arrangeMusic() {
        musicElement = new Element("div");
        musicElement.addClass("media-details");
        musicElement.appendElement("h1").text("name");

        Element tbody = musicElement.appendElement("table").appendElement("tbody");
        Element row = tbody.appendElement("tr");
        row.appendElement("th").text("Category");
        row.appendElement("td").text("Music");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Genre");
        row.appendElement("td").text("genre");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Format");
        row.appendElement("td").text("format");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Year");
        row.appendElement("td").text("1925");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Artist");
        row.appendElement("td").text("artist");
    }


    /**
     * Calls mapToModel method of the Model Mapper with proper book element.
     * Expected is an IModel which is instance of Book class.
     */
    @Test
    public void mapToModelReturnsNotNullBookIfElementPassedActuallyHasBook() {
        //arrange is in setUp

        //act
        try {
            IModel actual = mapper.mapToModel(bookElement);
            // assert
            Assert.assertTrue("The retrieved IModel is not a book.", actual instanceof Book);
        } catch (ScrapedDataDoesNotContainIModelException e) {
            Assert.fail();
        }
    }

    /**
     * Calls mapToModel method of the Model Mapper with proper movie element.
     * Expected is an IModel which is instance of Movie class.
     */
    @Test
    public void mapToModelReturnsNotNullMovieIfElementPassedActuallyHasMovie() {
        //arrange is in setUp

        //act
        try {
            IModel actual = mapper.mapToModel(movieElement);
            // assert
            Assert.assertTrue("The retrieved IModel is not a movie.",actual instanceof Movie);
        } catch (ScrapedDataDoesNotContainIModelException e) {
            Assert.fail();
        }
    }

    /**
     * Calls mapToModel method of the Model Mapper with proper music element.
     * Expected is an IModel which is instance of Music class.
     */
    @Test
    public void mapToModelReturnsNotNullMusicIfElementPassedActuallyHasMusic() {
        //arrange is in setUp

        //act
        try {
            IModel actual = mapper.mapToModel(musicElement);
            // assert
            Assert.assertTrue("The retrieved IModel is not music.",actual instanceof Music);
        } catch (ScrapedDataDoesNotContainIModelException e) {
            Assert.fail();
        }
    }

    /**
     * Calls mapToModel method of the Model Mapper with null value.
     * Expected is an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void mapToModelThrowsIllegalArgumentExceptionIfElementIsNull() throws ScrapedDataDoesNotContainIModelException {
        //arrange
        Element data = null;

        //act
        IModel actual = mapper.mapToModel(data);

        // assert is the expected exception
    }

    /**
     * Calls mapToModel method of the Model Mapper with book element with all details except Category.
     * Expected is an ScrapedDataDoesNotContainIModelException.
     */
    @Test(expected = ScrapedDataDoesNotContainIModelException.class)
    public void mapToModelThrowsScrapedDataDoesNotContainIModelExceptionIfElementHasNoCategory() throws ScrapedDataDoesNotContainIModelException {
        //arrange
        Element bookElement = new Element("div");
        bookElement.addClass("media-details");
        bookElement.appendElement("h1").text("name");

        Element tbody = bookElement.appendElement("table").appendElement("tbody");
        Element row = tbody.appendElement("tr");
        row.appendElement("th").text("Genre");
        row.appendElement("td").text("genre");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Format");
        row.appendElement("td").text("format");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Year");
        row.appendElement("td").text("1925");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Authors");
        row.appendElement("td").text("authors");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("Publisher");
        row.appendElement("td").text("publisher");

        row = tbody.appendElement("tr");
        row.appendElement("th").text("ISBN");
        row.appendElement("td").text("isbn");

        //act
        IModel actual = mapper.mapToModel(bookElement);

        // assert is the expected exception
    }
}