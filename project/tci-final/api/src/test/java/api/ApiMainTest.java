package api;

import main.LogicMain;
import models.*;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import serializers.GetAllSerializer;
import serializers.GetOneSerializer;
import serializers.RequestInfoSerializer;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.ArgumentMatchers.any;

/**
 * Author: B. Atanasov
 */
@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest(ApiMain.class)
public class ApiMainTest {
    private ApiMain apiMain;
    private static Movie movie;
    private static Book book;
    private static Music music;
    private static RequestInfo requestInfo;
    private Set<IModel> modelSetAll;
    private Set<IModel> modelSetOne;
    private static String requestInfoJson;
    private static String allJson;
    private static String oneJson;

    /**
     * Mock
     */
    @Mock
    LogicMain businessLogic;
    /**
     * Mock
     */
    @Mock RequestInfoSerializer requestInfoSerializer;
    /**
     * Mock
     */
    @Mock GetAllSerializer getAllSerializer;
    /**
     * Mock
     */
    @Mock GetOneSerializer getOneSerializer;

    private String testLog = "";
    /**
     * Rule
     */
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

    /**
     * Rule
     */
    @Rule
    public TestRule globalTimeout = Timeout.seconds(7);

    /**
     * Creates the static content needed for all the tests.
     */
    @BeforeClass
    public static void setStatic(){
        requestInfoJson = "{\"id\":1,\"time\":\"2s\",\"pagesExplored\":10,\"uniquePagesExplored\":5,\"searchDepth\":2}";
        oneJson = "{\"name\":\"Clasical\",\"format\":\"CD\",\"year\":2008,\"artist\":\"Ludwig van Beethoven\"}";
        allJson = "{\"movies\":[{\"name\":\"The Lord of the Rings: The Fellowship of the Ring\",\"genre\":\"Drama\"," +
                " \"format\":\"Blu-ray\",\"year\":2001, \"director\":\"Peter Jackson\",\"writers\":[],\"stars\":[]}]," +
                " \"books\":[{\"name\":\"The Clean Coder: A Code of Conduct for Professional Programmers\", " +
                "\"genre\":\"Tech\",\"format\":\"Audio\",\"year\":2011,\"authors\":\"Robert C.Martin\", " +
                "\"publisher\":\"Prentice Hall\",\"ISBN\":\"007-6092046981\"}],\"music\":[" +
                "{\"name\":\"Clasical\",\"format\":\"CD\",\"year\":2008,\"artist\":\"Ludwig van Beethoven\"}]}";

        movie = new Movie(
                "The Lord of the Rings: The Fellowship of the Ring",
                "Drama",
                "Blu-ray",
                2001,
                "Peter Jackson",
                new ArrayList<>(), new ArrayList<>()
        );

        book = new Book(
                "Clean Code: A Handbook of Agile Software Craftsmanship",
                "Tech",
                "Ebook",
                2008,
                "Robert C. Martin",
                "Prentice Hall",
                "978-0132350884"
        );

        music = new Music(
                "Clasical",
                "CD",
                "CD",
                2012,
                "Ludwig van Beethoven"
        );

        requestInfo = new RequestInfo(10, 5, 2);
    }

    /**
     * Creates all objects that need to be recreated for each test, mocks the functionality of the related classed and
     * replaces the fields in the main class with mocked once.
     * @throws IllegalAccessException is required for using PowerMock to switch the fields in a class.
     */
    @Before
    public void setUp() throws IllegalAccessException{
        apiMain = new ApiMain();
        modelSetAll = new HashSet<>();
        modelSetOne = new HashSet<>();

        modelSetAll.add(movie);
        modelSetAll.add(book);
        modelSetAll.add(music);
        modelSetOne.add(movie);

        Mockito.when(businessLogic.getAllFromUrl(any(URL.class))).thenReturn(new AbstractMap.SimpleEntry<>(requestInfo, modelSetAll));
        Mockito.when(businessLogic.getOneFromUrl(any(URL.class), any(String.class), any(String.class))).thenReturn(new AbstractMap.SimpleEntry<>(requestInfo, modelSetOne));
        Mockito.when(requestInfoSerializer.serializeToJson(requestInfo)).thenReturn(requestInfoJson);
        Mockito.when(getOneSerializer.serializeToJson(any(GetOne.class))).thenReturn(oneJson);
        Mockito.when(getAllSerializer.serializeToJson(any(GetAll.class))).thenReturn(allJson);
        MemberModifier.field(ApiMain.class, "businessLogic").set(apiMain, businessLogic);
        MemberModifier.field(ApiMain.class, "requestInfoSerializer").set(apiMain, requestInfoSerializer);
        MemberModifier.field(ApiMain.class, "getAllSerializer").set(apiMain, getAllSerializer);
        MemberModifier.field(ApiMain.class, "getOneSerializer").set(apiMain, getOneSerializer);
        MemberModifier.field(ApiMain.class, "fileName").set(apiMain, "../test.json");
    }

    /**
     * Calls the getAll method and tests the happy path with the static content.
     */
    @Test
    public void getAllRequestsResponseContentAsExpected() {
        Response result = apiMain.getAll("http://www.example.com");

        Assert.assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        Assert.assertEquals(allJson, result.getEntity().toString());
    }

    /**
     * Calls the getAll method and checks if the writeRequestInfoToFile was invoked.
     */
    @Test
    public void getAllInvokesWriteToFileWithRequestInfoObject() throws Exception {
        apiMain = PowerMockito.spy(new ApiMain());
        MemberModifier.field(ApiMain.class, "businessLogic").set(apiMain, businessLogic);
        MemberModifier.field(ApiMain.class, "requestInfoSerializer").set(apiMain, requestInfoSerializer);
        MemberModifier.field(ApiMain.class, "getAllSerializer").set(apiMain, getAllSerializer);
        MemberModifier.field(ApiMain.class, "getOneSerializer").set(apiMain, getOneSerializer);

        apiMain.getAll("http://www.example.com");

        PowerMockito.verifyPrivate(apiMain).invoke("writeRequestInfoToFile", requestInfo);
    }

    /**
     * Calls the getAll method and tests that a NOT_FOUND response is returned when no models are found.
     * Modifies the businessLogic variable to return a pair of RequestInfo and empty model set.
     */
    @Test
    public void getAllRequestReturnsCode404NotFoundIfThereWereNoModelsToReturn() throws IllegalAccessException {
        Mockito.when(businessLogic.getAllFromUrl(any(URL.class))).thenReturn(new AbstractMap.SimpleEntry<>(requestInfo, new HashSet<>()));
        MemberModifier.field(ApiMain.class, "businessLogic").set(apiMain, businessLogic);

        Response result = apiMain.getAll("http://www.example.com");

        Assert.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), result.getStatus());
        Assert.assertEquals("No records found!", result.getEntity());
    }

    /**
     * Calls the getAll method and tests that a BAD_REQUEST response is returned when the provided url is not in the
     * correct format.
     */
    @Test
    public void getAllRequestReturnsCode400BadRequestIfUrlIsNotInTheRightFormat() {
        Response result = apiMain.getAll("adsasda");

        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
        Assert.assertEquals("Not a real url!", result.getEntity());
    }

    /**
     * Calls the getAll method and tests the happy path with the static content.
     */
    @Test
    public void getOneRequestResponseContentAsExpected() {
        Response result = apiMain.getOne("http://www.example.com", "", "");

        Assert.assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        Assert.assertEquals(oneJson, result.getEntity().toString());
    }

    /**
     * Calls the getOne method and tests that a NOT_FOUND response is returned when no model is found.
     * Modifies the businessLogic variable to return a pair of RequestInfo and empty model set.
     */
    @Test
    public void getOneRequestReturnsCode404NotFoundIfThereWasNotSuchModel() throws IllegalAccessException {
        Mockito.when(businessLogic.getOneFromUrl(any(URL.class), any(String.class), any(String.class)))
                .thenReturn(new AbstractMap.SimpleEntry<>(requestInfo, new HashSet<>()));
        MemberModifier.field(ApiMain.class, "businessLogic").set(apiMain, businessLogic);

        Response result = apiMain.getOne("http://www.example.com", "", "");

        Assert.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), result.getStatus());
        Assert.assertEquals("No record found!", result.getEntity());
    }

    /**
     * Calls the getOne method and tests that a BAD_REQUEST response is returned when the provided url is not in the
     * correct format.
     */
    @Test
    public void getOneRequestReturnsCode400BadRequestIfUrlIsNotInTheRightFormat() {
        Response result = apiMain.getOne("adsasda", "", "");

        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
        Assert.assertEquals("Not a url!", result.getEntity());
    }

    /**
     * Calls the getLastRequest method and tests the happy path with the static content.
     */
    @Test
    public void getLastRequestResponseContentAsExpected(){
        Response result = apiMain.getLastRequest();

        Assert.assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        Assert.assertEquals(requestInfoJson, result.getEntity());
    }

    /**
     * Calls the getLastRequest method and tests that NOT_FOUND response will be returned if the file that contains
     * the info for the last request does exists.
     * Modifies the fileName variable to use a non-existing file.
     * @throws IllegalAccessException required due to the use of MemberModifier.
     */
    @Test
    public void getLastRequestReturnsCode404NotFoundIfReadFromFileReturnsNull() throws IllegalAccessException {
        MemberModifier.field(ApiMain.class, "fileName").set(apiMain, "../nonExisting.json");

        Response result = apiMain.getLastRequest();

        Assert.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), result.getStatus());
        Assert.assertEquals("No record found!", result.getEntity());
    }
}