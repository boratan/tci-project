package serializers;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;

import static org.junit.Assert.*;

public class MusicSerializerTest {

    @Test
    public void afterConstructionGsonObjectCanBeReturned() {

    }

    @Test
    public void serializeToJsonReturnsValidString() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void serializeToJsonReturnsInvalidArgumentExceptionIfMusicIsNull() {

    }

    @Test
    public void deserializeFromJsonReturnsMusicThatIsNotNull() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsNull() {

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsEmpty() {

    }
}