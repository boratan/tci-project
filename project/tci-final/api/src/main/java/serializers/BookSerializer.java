package serializers;

import models.Book;

public class BookSerializer implements IGenericSerializer<Book> {

    /**
     * Serializes the Book model to JSON format.
     * @param object
     * @return
     */
    @Override
    public String serializeToJson(Book object) {
        return null;
    }

    /**
     * De-serializes JSON string to Book model.
     * @param json
     * @return
     */
    @Override
    public Book deserializeFromJson(String json) {
        return null;
    }
}
