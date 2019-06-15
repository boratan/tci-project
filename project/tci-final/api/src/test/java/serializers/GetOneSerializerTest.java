package serializers;

import models.GetOne;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: M. Andreeva
 */
public class GetOneSerializerTest {

    private GetOneSerializer serializer;

    @Before
    public void setUp() {
        serializer = new GetOneSerializer();
    }

    /**
     * The test creates null GetOne object and passes it to serializeToJson method of SUT.
     */
    @Test(expected = IllegalArgumentException.class)
    public void serializeToJsonThrowsIllegalArgumentExceptionIfGetOneIsNull() {
        //arrange
        GetOne model = null;

        //act
        String result = serializer.serializeToJson(model);

        //assert is the expected exception
    }

    /**
     * The test creates null string and passes it to deserializeFromJson method of SUT.
     */
    @Test(expected = IllegalArgumentException.class)
    public void deserializeFromJsonReturnsIllegalArgumentExceptionIfStringIsNull() {
        //arrange
        String input = null;

        //act
        GetOne result = serializer.deserializeFromJson(input);

        //assert is the expected exception
    }

    /**
     * The test creates empty string and passes it to deserializeFromJson method of SUT.
     */
    @Test(expected = IllegalArgumentException.class)
    public void deserializeFromJsonReturnsIllegalArgumentExceptionIfStringIsEmpty() {
        //arrange
        String input = "";

        //act
        GetOne result = serializer.deserializeFromJson(input);

        //assert is the expected exception
    }
}