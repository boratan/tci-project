package models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static org.mockito.Mockito.mock;

/**
 * Author: M. Andreeva
 */
public class EnrichedUrlTest {

    private EnrichedUrl object;
    private URL url = mock(URL.class);

    @Before
    public void setUp() {
        object = new EnrichedUrl(url, 2);
    }

    /**
     * The test invokes getURL of SUT. Expected is not null URL object.
     *
     * The test makes use of custom assertion messages.
     */
    @Test
    public void afterConstructionNotNullURLCanBeReturned() {
        //arrange is done in setUp

        //act
        URL actual = object.getUrl();

        //assert
        Assert.assertNotNull("After construction URL is null!", actual);
    }

    /**
     * The test invokes getDepth of SUT. Expected is not null Integer object.
     *
     * The test makes use of custom assertion messages.
     */
    @Test
    public void afterConstructionNotNullDepthCanBeReturned() {
        //arrange is done in setUp

        //act
        Integer actual = object.getDepth();

        //assert
        Assert.assertNotNull("After construction depth is null!", actual);
    }

    /**
     * The test passes null URL to setURL method of SUT. Expected is an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setUrlThrowsIllegalArgumentExceptionIfURLIsNull() {
        //arrange
        URL toPass = null;

        //act
        object.setUrl(toPass);

        //assert is the expected exception
    }

    /**
     * The test passes null Integer to setDepth method of SUT. Expected is an exception.
     */
    @Test(expected = IllegalArgumentException.class)
    public void setDepthThrowsIllegalArgumentExceptionIfDepthIsNull() {
        //arrange
        Integer toPass = null;

        //act
        object.setDepth(toPass);

        //assert is the expected exception
    }

}