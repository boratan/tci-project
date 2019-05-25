package serializers;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovieSerializerTest {

    @Test
    public void afterConstructionGsonObjectCanBeReturned() {

    }

    @Test
    public void serializeToJsonReturnsValidString() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void serializeToJsonReturnsInvalidArgumentExceptionIfMovieIsNull() {

    }

    @Test
    public void deserializeFromJsonReturnsMovieThatIsNotNull() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsNull() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsEmpty() {

    }

}