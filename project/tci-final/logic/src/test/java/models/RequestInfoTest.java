package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestInfoTest {
    private RequestInfo requestInfo;

    @Before
    public void setUp(){
        requestInfo = new RequestInfo(10, 5, 2);
    }


    @Test
    public void RequestPagesExploredIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(requestInfo.getPagesExplored());
    }

    @Test
    public void RequestUniquePagesExploredNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(requestInfo.getUniquePagesExplored());
    }

    @Test
    public void RequestSearchDepthIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(requestInfo.getSearchDepth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void RequestIdReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setId(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RequestIdReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setId(-3);
    }

    @Test (expected = IllegalArgumentException.class)
    public void RequestTimeReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setTime(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void RequestTimeReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setTime(-3);
    }

    @Test
    public void RequestPagesExploredReturnedIsInvoked(){
        Integer pagesE = 10;

        Assert.assertEquals(pagesE, requestInfo.getPagesExplored());
    }

    @Test
    public void RequestUniquePagesExploredReturnedIsInvoked(){
        Integer pagesU = 5;

        Assert.assertEquals(pagesU, requestInfo.getUniquePagesExplored());
    }

    @Test
    public void RequestSearchDepthReturnedIsInvoked(){
        Integer depth = 2;

        Assert.assertEquals(depth, requestInfo.getSearchDepth());
    }
}