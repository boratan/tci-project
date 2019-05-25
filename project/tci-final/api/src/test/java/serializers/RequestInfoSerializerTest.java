package serializers;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestInfoSerializerTest {

    @Test
    public void afterConstructionGsonObjectCanBeReturned() {

    }

    @Test
    public void serializeToJsonReturnsValidString() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void serializeToJsonReturnsInvalidArgumentExceptionIfRequestInfoIsNull() {

    }

    @Test
    public void deserializeFromJsonReturnsRequestInfoThatIsNotNull() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsNull() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsEmpty() {

    }
}