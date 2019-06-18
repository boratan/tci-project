package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Author: B. Atanasov
 */
public class RequestInfoTest {
    private RequestInfo requestInfo;

    @Before
    public void setUp(){
        requestInfo = new RequestInfo(10, 5, 2);
    }


    /**
     * Checks that pagesExplored is not null after the creation of the RequestInfo object.
     */
    @Test
    public void RequestPagesExploredIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(requestInfo.getPagesExplored());
    }

    /**
     * Checks that uniquePagesExplored is not null after the creation of the RequestInfo object.
     */
    @Test
    public void RequestUniquePagesExploredNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(requestInfo.getUniquePagesExplored());
    }

    /**
     * Checks that searchDepth is not null after the creation of the RequestInfo object.
     */
    @Test
    public void RequestSearchDepthIsNotNullWhenConstructorIsInvoked(){
        Assert.assertNotNull(requestInfo.getSearchDepth());
    }

    /**
     * Verifies that NullPointerException is thrown if the id is set to null.
     */
    @Test(expected = NullPointerException.class)
    public void RequestIdReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setId(null);
    }

    /**
     * Verifies that IllegalArgumentException is thrown if the id is set to a negative value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void RequestIdReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setId(-3);
    }


    /**
     * Verifies that NullPointerException is thrown if the time is set to null.
     */
    @Test (expected = NullPointerException.class)
    public void RequestTimeReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setTime(null);
    }

    /**
     * Verifies that IllegalArgumentException is thrown if the time is set to a negative value.
     */
    @Test (expected = IllegalArgumentException.class)
    public void RequestTimeReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setTime(-3);
    }

    /**
     * Verifies that NullPointerException is thrown if the uniquePagesExplored is set to null.
     */
    @Test(expected = NullPointerException.class)
    public void RequestUniquePagesExploredReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setUniquePagesExplored(null);
    }

    /**
     * Verifies that IllegalArgumentException is thrown if the uniquePagesExplored is set to a negative value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void RequestUniquePagesExploredReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setUniquePagesExplored(-3);
    }

    /**
     * Verifies that NullPointerException is thrown if the pagesExplored is set to null.
     */
    @Test(expected = NullPointerException.class)
    public void RequestPagesExploredReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setPagesExplored(null);
    }

    /**
     * Verifies that IllegalArgumentException is thrown if the pagesExplored is set to a negative value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void RequestPagesExploredReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setPagesExplored(-3);
    }

    /**
     * Verifies that NullPointerException is thrown if the searchDepth is set to null.
     */
    @Test(expected = NullPointerException.class)
    public void RequestSearchDepthReturnsLegalArgumentExceptionIfSetToNull(){
        requestInfo.setSearchDepth(null);
    }

    /**
     * Verifies that IllegalArgumentException is thrown if the searchDepth is set to a negative value.
     */
    @Test(expected = IllegalArgumentException.class)
    public void RequestSearchDepthReturnsLegalArgumentExceptionIfSetToNegative(){
        requestInfo.setSearchDepth(-3);
    }

    /**
     * Checks that the correct value of pagesExplored is set after the creation of the RequestInfo object.
     */
    @Test
    public void RequestPagesExploredReturnedIsInvoked(){
        Integer pagesE = 10;

        Assert.assertEquals(pagesE, requestInfo.getPagesExplored());
    }

    /**
     * Checks that the correct value of uniquePagesExplored is set after the creation of the RequestInfo object.
     */
    @Test
    public void RequestUniquePagesExploredReturnedIsInvoked(){
        Integer pagesU = 5;

        Assert.assertEquals(pagesU, requestInfo.getUniquePagesExplored());
    }

    /**
     * Checks that the correct value of searchDepth is set after the creation of the RequestInfo object.
     */
    @Test
    public void RequestSearchDepthReturnedIsInvoked(){
        Integer depth = 2;

        Assert.assertEquals(depth, requestInfo.getSearchDepth());
    }
}