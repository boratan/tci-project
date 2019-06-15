package mappers;
import models.Book;
import models.IModel;
import models.Movie;
import models.Music;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.lang.model.element.Element;

public class ModelMapper {

    /**
     * Checks the category of the data provided and calls a method to map
     * to a IModel instance accordingly.
     * Returns ScrapedDataDoesNotContainIModelException if no category is found.
     * @param data
     * @return
     */
    public IModel mapToModel(Element data) { throw new NotImplementedException(); }

    /**
     * Maps data from the scraper to Book model.
     * Returns ScrapedDataDoesNotContainIModelException if data has not book.
     * @param data
     * @return
     */
    private Book mapToBook(Element data) {
        throw new NotImplementedException();
    }

    /**
     * Maps data from the scraper to Movie model.
     * Returns ScrapedDataDoesNotContainIModelException if data has not book.
     * @param data
     * @return
     */
    private Movie mapToMovie(Element data) {
        throw new NotImplementedException();
    }

    /**
     * Maps data from the scraper to Music model.
     * Returns ScrapedDataDoesNotContainIModelException if data has not book.
     * @param data
     * @return
     */
    private Music mapToMusic(Element data) {
        throw new NotImplementedException();
    }
}
