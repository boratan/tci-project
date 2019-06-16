package serializers;

import org.junit.Before;
import org.junit.Test;

public class RequestInfoSerializerTest {
    private RequestInfoSerializer requestInfoSerializer;

    @Before
    public void setUp(){
        requestInfoSerializer = new RequestInfoSerializer();
    }

    @Test(expected = IllegalArgumentException.class)
    public void serializeToJsonReturnsInvalidArgumentExceptionIfRequestInfoIsNull() {
        requestInfoSerializer.serializeToJson(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsNull() {
        requestInfoSerializer.deserializeFromJson(null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsEmpty() {
        String json = "";
        requestInfoSerializer.deserializeFromJson(json);
    }
}