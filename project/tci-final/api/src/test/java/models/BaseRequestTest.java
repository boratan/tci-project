package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseRequestTest {
    private BaseRequest baseRequest;

    @Before
    public void setUp(){
        baseRequest = new BaseRequest();
    }

    @Test
    public void afterConstructionIntIdCanBeReturned() {
        int id = baseRequest.getId();

        Assert.assertNotNull(id);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void afterConstructionLongTimeInMilliCanBeReturned() {
        long time = baseRequest.getTimeInMilli();

        Assert.assertNotNull(time);
        Assert.assertTrue(time > 0);
    }
}