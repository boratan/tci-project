package serializers;

import org.junit.Before;
import org.junit.Test;

/**
 * Author: B. Atanasov
 */
public class RequestInfoSerializerTest {
    private RequestInfoSerializer requestInfoSerializer;

    /**
     * setup
     */
    @Before
    public void setUp(){
        requestInfoSerializer = new RequestInfoSerializer();
    }

    /**
     * Calls the serializeToJson with a null as an object parameter and verifies that in that case an
     * IllegalArgumentException is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void serializeToJsonReturnsInvalidArgumentExceptionIfRequestInfoIsNull() {
        requestInfoSerializer.serializeToJson(null);
    }

    /**
     * Calls the deserializeFromJson with a null as an jsonString parameter and verifies that in that case an
     * IllegalArgumentException is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsNull() {
        requestInfoSerializer.deserializeFromJson(null);

    }

    /**
     * Calls the deserializeFromJson with an empty string as an jsonString parameter and verifies that in that case an
     * IllegalArgumentException is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsEmpty() {
        String json = "";
        requestInfoSerializer.deserializeFromJson(json);
    }
}