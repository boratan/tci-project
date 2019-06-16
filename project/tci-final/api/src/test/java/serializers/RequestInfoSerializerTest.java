package serializers;

import com.sun.javaws.exceptions.InvalidArgumentException;
import models.RequestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RequestInfoSerializerTest {
    private RequestInfoSerializer requestInfoSerializer;

    @Before
    public void setUp(){
        requestInfoSerializer = new RequestInfoSerializer();
    }

    @Test(expected = InvalidArgumentException.class)
    public void serializeToJsonReturnsInvalidArgumentExceptionIfRequestInfoIsNull() {
        requestInfoSerializer.serializeToJson(null);
    }

    @Test
    public void deserializeFromJsonReturnsRequestInfo() {
        String json = "{'id':3, 'time':153255, 'pagesExplored':10, 'uniquePagesExplored':5, 'searchDepth':2}";
        RequestInfo requestInfo = new RequestInfo(10, 5, 2);
        requestInfo.setId(3);
        requestInfo.setTime(153255);

        Assert.assertEquals(requestInfo, requestInfoSerializer.deserializeFromJson(json));
    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsNull() {
        requestInfoSerializer.deserializeFromJson(null);

    }

    @Test(expected = InvalidArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsEmpty() {
        String json = "";
        requestInfoSerializer.deserializeFromJson(json);
    }
}