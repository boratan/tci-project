import javafx.util.Pair;
import models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.net.ssl.*")
@PrepareForTest(ApiMain.class)
public class ApiMainTest {
    private ApiMain apiMain;
    private Movie movie;
    private Book book;
    private Music music;
    private RequestInfo requestInfo;
    private Set<IModel> modelSetAll;
    private Set<IModel> modelSetOne;
    private String requestInfoJson;
    private String allJson;
    private String oneJson;

    @Mock LogicMain businessLogic;
    @Mock RequestInfoSerializer requestInfoSerializer;
    @Mock GetAllSerializer getAllSerializer;
    @Mock GetOneSerializer getOneSerializer;

    @Before
    public void setUp() throws IllegalAccessException{
        apiMain = new ApiMain();
        modelSetAll = new HashSet<>();
        modelSetOne = new HashSet<>();
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
                "2012",
                "Ludwig van Beethoven"
        );

        requestInfo = new RequestInfo(10, 5, 2);

        modelSetAll.add(movie);
        modelSetAll.add(book);
        modelSetAll.add(music);
        modelSetOne.add(movie);

        Mockito.when(businessLogic.getAllFromUrl(any(URL.class))).thenReturn(new Pair<>(requestInfo, modelSetAll));
        Mockito.when(businessLogic.getOneFromUrl(any(URL.class), any(String.class), any(String.class))).thenReturn(new Pair<>(requestInfo, modelSetOne));
        Mockito.when(requestInfoSerializer.serializeToJson(requestInfo)).thenReturn(requestInfoJson);
        Mockito.when(getOneSerializer.serializeToJson(any(GetOne.class))).thenReturn(oneJson);
        Mockito.when(getAllSerializer.serializeToJson(any(GetAll.class))).thenReturn(allJson);
        MemberModifier.field(ApiMain.class, "businessLogic").set(apiMain, businessLogic);
        MemberModifier.field(ApiMain.class, "requestInfoSerializer").set(apiMain, requestInfoSerializer);
        MemberModifier.field(ApiMain.class, "getAllSerializer").set(apiMain, getAllSerializer);
        MemberModifier.field(ApiMain.class, "getOneSerializer").set(apiMain, getOneSerializer);
    }

    @Test
    public void getAllRequestsResponseContentAsExpected() {
        Response result = apiMain.getAll("http://www.example.com");

        Assert.assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        Assert.assertEquals(requestInfoJson + allJson, result.getEntity().toString());
    }

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

    @Test
    public void getAllRequestReturnsCode404NotFoundIfThereWereNoModelsToReturn() throws IllegalAccessException {
        Mockito.when(businessLogic.getAllFromUrl(any(URL.class))).thenReturn(new Pair<>(requestInfo, new HashSet<>()));
        MemberModifier.field(ApiMain.class, "businessLogic").set(apiMain, businessLogic);

        Response result = apiMain.getAll("http://www.example.com");

        Assert.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), result.getStatus());
        Assert.assertEquals("No records found!", result.getEntity());
    }

    @Test
    public void getAllRequestReturnsCode400BadRequestIfUrlIsNotInTheRightFormat() {
        Response result = apiMain.getAll("adsasda");

        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
        Assert.assertEquals("Not a real url!", result.getEntity());
    }

    @Test
    public void getOneRequestResponseContentAsExpected() {
        Response result = apiMain.getOne("http://www.example.com", "", "");

        Assert.assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        Assert.assertEquals(requestInfoJson + oneJson, result.getEntity().toString());
    }

    @Test
    public void getOneRequestReturnsCode404NotFoundIfThereWasNotSuchModel() throws IllegalAccessException {
        Mockito.when(businessLogic.getOneFromUrl(any(URL.class), any(String.class), any(String.class)))
                .thenReturn(new Pair<>(requestInfo, new HashSet<>()));
        MemberModifier.field(ApiMain.class, "businessLogic").set(apiMain, businessLogic);

        Response result = apiMain.getOne("http://www.example.com", "", "");

        Assert.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), result.getStatus());
        Assert.assertEquals("No record found!", result.getEntity());
    }

    @Test
    public void getOneRequestReturnsCode400BadRequestIfUrlIsNotInTheRightFormat() {
        Response result = apiMain.getOne("adsasda", "", "");

        Assert.assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), result.getStatus());
        Assert.assertEquals("Not a url!", result.getEntity());
    }

    @Test
    public void getLastRequestResponseContentAsExpected() {
        Response result = apiMain.getLastRequest();

        Assert.assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        Assert.assertEquals(requestInfoJson, result.getEntity());
    }

    @Test
    public void getLastRequestReturnsCode404NotFoundIfReadFromFileReturnsNull() throws IllegalAccessException {
        MemberModifier.field(ApiMain.class, "fileName").set(apiMain, "../nonExisting.json");

        Response result = apiMain.getLastRequest();

        Assert.assertEquals(Response.Status.NOT_FOUND.getStatusCode(), result.getStatus());
        Assert.assertEquals("No record found!", result.getEntity());
    }
}