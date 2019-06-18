package models;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.mockito.Mockito.mock;

/**
 * Author: M. Andreeva
 */
public class GetOneTest {

    /**
     * The test creates mock of IModel to add to a set. The set is then used in the creation of the GetOne model.
     * Then the method getResult of the model is invoked with expected not null return.
     *
     * The method makes use of dummy mock object for direct input.
     */
    @Test
    public void afterConstructionNotNullIModelCanBeReturned() {
        //arrange
        Book book = mock(Book.class);
        Set<IModel> argument = new HashSet<IModel>();
        argument.add(book);

        //act
        GetOne model = new GetOne(argument);

        //assert
        Assert.assertNotNull(model.getResult());
    }

    /**
     * The test creates set, sets it to null and passes it to GetModel constructor.
     */
    @Test(expected = NullPointerException.class)
    public void constructionThrowsNullPointerExceptionIfSetOfModelsIsNull() {
        //arrange
        Set<IModel> argument = null;

        //act
        GetOne model = new GetOne(argument);

        //assert is the expected exception
    }

    /**
     * The test creates set, sets it to an empty set and passes it to GetModel constructor.
     */
    @Test(expected = IllegalArgumentException.class)
    public void constructionThrowsIllegalArgumentExceptionIfSetOfModelsIsEmpty() {
        //arrange
        Set<IModel> argument = Collections.emptySet();

        //act
        GetOne model = new GetOne(argument);

        //assert is the expected exception
    }
}