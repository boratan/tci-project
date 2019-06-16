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

    @Test(expected = NullPointerException.class)
    public void RequestIdReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setId(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RequestIdReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setId(-3);
    }

    @Test (expected = NullPointerException.class)
    public void RequestTimeReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setTime(null);
    }

    @Test (expected = IllegalArgumentException.class)
    public void RequestTimeReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setTime(-3);
    }

    @Test(expected = NullPointerException.class)
    public void RequestUniquePagesExploredReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setUniquePagesExplored(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RequestUniquePagesExploredReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setUniquePagesExplored(-3);
    }

    @Test(expected = NullPointerException.class)
    public void RequestPagesExploredReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setPagesExplored(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RequestPagesExploredReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setPagesExplored(-3);
    }

    @Test(expected = NullPointerException.class)
    public void RequestSearchDepthReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setSearchDepth(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void RequestSearchDepthReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setSearchDepth(-3);
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