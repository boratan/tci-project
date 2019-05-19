package serializers;

import models.Movie;

public class MovieSerializer implements IGenericSerializer<Movie> {

    /**
     * Serializes the Movie model to JSON format.
     * @param object
     * @return
     */
    @Override
    public String serializeToJson(Movie object) {
        return null;
    }

    /**
     * De-serializes JSON string to Movie model.
     * @param json
     * @return
     */
    @Override
    public Movie deserializeFromJson(String json) {
        return null;
    }
}
