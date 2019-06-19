package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author: B. Atanasov
 */
public class BaseRequestTest {
    private BaseRequest baseRequest;

    /**
     * Setup
     */
    @Before
    public void setUp(){
        baseRequest = new BaseRequest();
    }

    /**
     * Verifies that the BaseRequest id is bigger then 0 when after the object is initialized.
     */
    @Test
    public void afterConstructionIntIdCanBeReturned() {
        int id = baseRequest.getId();

        Assert.assertTrue(id > 0);
    }

    /**
     * Verifies that the BaseRequest time is bigger then 0 when after the object is initialized.
     */
    @Test
    public void afterConstructionLongTimeInMilliCanBeReturned() {
        long time = baseRequest.getTimeInMilli();

        Assert.assertTrue(time > 0);
    }

    /**
     * Verifies that the BaseRequest id is incremented with each new instance of the object starting form 0.
     */
    @Test
    public void idIsIncrementedWithEachNewInstance() {
        int id1 = baseRequest.getId();
        baseRequest = new BaseRequest();
        int id2 = baseRequest.getId();
        baseRequest = new BaseRequest();
        int id3 = baseRequest.getId();

        Assert.assertEquals(3, id1);
        Assert.assertEquals(4, id2);
        Assert.assertEquals(5, id3);
    }
}