package models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BaseRequestTest {

    @Test
    public void afterConstructionIntIdCanBeReturned() {
        BaseRequest request = new BaseRequest();

        Integer id = request.getId();

        Assert.assertNotNull(id);
        Assert.assertTrue(id > 0);
    }

    @Test
    public void afterConstructionLongTimeInMilliCanBeReturned() {
        BaseRequest request = new BaseRequest();

        request.getTimeInMilli();

        Assert.assertNotNull(id);
        Assert.assertTrue(id > 0);
    }
}