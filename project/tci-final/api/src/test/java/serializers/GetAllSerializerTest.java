package serializers;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;

import static org.junit.Assert.*;

public class GetAllSerializerTest {

    @Test
    public void afterConstructionGsonObjectCanBeReturned() {

    }

    @Test
    public void serializeToJsonReturnsValidString() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void serializeToJsonReturnsInvalidArgumentExceptionIfGetAllIsNull() {

    }

    @Test
    public void deserializeFromJsonReturnsGetAllThatIsNotNull() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsNull() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsEmpty() {

    }
}