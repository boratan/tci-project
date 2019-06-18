package serializers;

import models.GetAll;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;

public class GetAllSerializerTest {

    private GetAllSerializer gas;

    private String testLog = "";
    @Rule
    public final TestRule watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            testLog += String.format("Test %s started\n", description);
        }

        @Override
        protected void succeeded(Description description) {
            testLog += String.format("Test %s succeeded", description);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            testLog += String.format("Test %s failed", description);
        }

        @Override
        protected void finished(Description description) {
            System.out.println(testLog);
        }
    };

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Rule
    public TestRule globalTimeout = Timeout.seconds(7);

    @Before
    public void setUp(){
        gas = new GetAllSerializer();
    }

    /**
     * When the serializeToJson is called with null argument,
     * Illegal ArgumentException is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void serializeToJsonReturnsIllegalArgumentExceptionIfGetAllIsNull() {
        GetAll ga = null;
        String jsonResult = gas.serializeToJson(ga);
    }

    /**
     * When the deserializeToJson is called with null argument,
     * Illegal ArgumentException is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsNull() {
        String testString = null;
        GetAll ga = gas.deserializeFromJson(testString);
    }

    /**
     * When the deserializeToJson is called with argument empty Set<IModel>,
     * Illegal ArgumentException is thrown.
     */
    @Test(expected = IllegalArgumentException.class)
    public void deserializeFromJsonReturnsInvalidArgumentExceptionIfStringIsEmpty() {
        String testString = "";
        GetAll ga = gas.deserializeFromJson(testString);
    }
}