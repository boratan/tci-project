package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: B. Atanasov
 */
public class RequestInfoTest {
    private RequestInfo requestInfo;

    /**
     * setup
     */
    @Before
    public void setUp(){
        requestInfo = new RequestInfo(10, 5, 2);
    }


    /**
     * Checks that pagesExplored is not null after the creation of the RequestInfo object.
     */
    @Test
    public void requestPagesExploredIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(requestInfo.getPagesExplored());
    }

    /**
     * Checks that uniquePagesExplored is not null after the creation of the RequestInfo object.
     */
    @Test
    public void requestUniquePagesExploredNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(requestInfo.getUniquePagesExplored());
    }

    /**
     * Checks that searchDepth is not null after the creation of the RequestInfo object.
     */
    @Test
    public void requestSearchDepthIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(requestInfo.getSearchDepth());
    }

    /**
     * Verifies that NullPointerException is thrown if the id is set to null.
     */
    @Test(expected = NullPointerException.class)
    public void requestIdReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setId(null);
    }

    /**
     * Verifies that IllegalArgumentException is thrown if the id is set to a negative value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void requestIdReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setId(-3);
    }


    /**
     * Verifies that NullPointerException is thrown if the time is set to null.
     */
    @Test (expected = NullPointerException.class)
    public void requestTimeReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setTime(null);
    }

    /**
     * Verifies that IllegalArgumentException is thrown if the time is set to a negative value.
     */
    @Test (expected = IllegalArgumentException.class)
    public void requestTimeReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setTime(-3);
    }

    /**
     * Verifies that NullPointerException is thrown if the uniquePagesExplored is set to null.
     */
    @Test(expected = NullPointerException.class)
    public void requestUniquePagesExploredReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setUniquePagesExplored(null);
    }

    /**
     * Verifies that IllegalArgumentException is thrown if the uniquePagesExplored is set to a negative value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void requestUniquePagesExploredReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setUniquePagesExplored(-3);
    }

    /**
     * Verifies that NullPointerException is thrown if the pagesExplored is set to null.
     */
    @Test(expected = NullPointerException.class)
    public void requestPagesExploredReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setPagesExplored(null);
    }

    /**
     * Verifies that IllegalArgumentException is thrown if the pagesExplored is set to a negative value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void requestPagesExploredReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setPagesExplored(-3);
    }

    /**
     * Verifies that NullPointerException is thrown if the searchDepth is set to null.
     */
    @Test(expected = NullPointerException.class)
    public void requestSearchDepthReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setSearchDepth(null);
    }

    /**
     * Verifies that IllegalArgumentException is thrown if the searchDepth is set to a negative value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void requestSearchDepthReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setSearchDepth(-3);
    }

    /**
     * Checks that the correct value of pagesExplored is set after the creation of the RequestInfo object.
     */
    @Test
    public void requestPagesExploredReturnedIsInvoked(){
        Integer pagesE = 10;

        Assert.assertEquals(pagesE, requestInfo.getPagesExplored());
    }

    /**
     * Checks that the correct value of uniquePagesExplored is set after the creation of the RequestInfo object.
     */
    @Test
    public void requestUniquePagesExploredReturnedIsInvoked(){
        Integer pagesU = 5;

        Assert.assertEquals(pagesU, requestInfo.getUniquePagesExplored());
    }

    /**
     * Checks that the correct value of searchDepth is set after the creation of the RequestInfo object.
     */
    @Test
    public void requestSearchDepthReturnedIsInvoked(){
        Integer depth = 2;

        Assert.assertEquals(depth, requestInfo.getSearchDepth());
    }
}