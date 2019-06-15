package serializers;

import models.GetAll;
import org.junit.Before;
import org.junit.Test;

public class GetAllSerializerTest {

    private GetAllSerializer gas;

    @Before
    public void setUp(){
        gas = new GetAllSerializer();
    }

    @Test(expected = IllegalArgumentException.class)
    public void serializeToJsonReturnsIllegalArgumentExceptionIfGetAllIsNull() {
        GetAll ga = null;
        String jsonResult = gas.serializeToJson(ga);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsNull() {
        String testString = null;
        GetAll ga = gas.deserializeFromJson(testString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsEmpty() {
        String testString = "";
        GetAll ga = gas.deserializeFromJson(testString);
    }
}