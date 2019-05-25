package serializers;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookSerializerTest {

    @Test
    public void afterConstructionGsonObjectCanBeReturned() {

    }

    @Test
    public void serializeToJsonReturnsValidString() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void serializeToJsonReturnsInvalidArgumentExceptionIfBookIsNull() {

    }

    @Test
    public void deserializeFromJsonReturnsBookThatIsNotNull() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsNull() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsEmpty() {

    }
}