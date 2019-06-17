import io.restassured.RestAssured;
import javafx.util.Pair;
import models.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.api.support.membermodification.MemberModifier;
import serializers.GetAllSerializer;
import serializers.GetOneSerializer;
import serializers.RequestInfoSerializer;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

public class ApiMainTest {
    private ApiMain apiMain;
    private Movie movie;
    private Book book;
    private Music music;
    private RequestInfo requestInfo;
    private Set<IModel> modelSetAll;
    private Set<IModel> modelSetOne;

    @Mock LogicMain businessLogic;
    @Mock RequestInfoSerializer requestInfoSerializer;
    @Mock GetAllSerializer getAllSerializer;
    @Mock GetOneSerializer getOneSerializer;
    @Mock URL url;

    @Before
    public void setUp() throws IllegalAccessException {
        apiMain = new ApiMain();
        modelSetAll = new HashSet<>();
        modelSetOne = new HashSet<>();

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
        modelSetAll.add(movie);

        when(businessLogic.getAllFromUrl(url)).thenReturn(new Pair<>(requestInfo, modelSetAll));
        when(businessLogic.getOneFromUrl(url, "", "")).thenReturn(new Pair<>(requestInfo, modelSetOne));
        MemberModifier.field(ApiMain.class, "businessLogic").set(apiMain, businessLogic);
        MemberModifier.field(ApiMain.class, "requestInfoSerializer").set(apiMain, requestInfoSerializer);
        MemberModifier.field(ApiMain.class, "getAllSerializer").set(apiMain, getAllSerializer);
        MemberModifier.field(ApiMain.class, "getOneSerializer").set(apiMain, getOneSerializer);
    }

    @Test
    public void getAllRequestsResponseContentTypeIsJson() {
        RestAssured.get("/all").then().assertThat().body("lotto.lottoId", equalTo(5));
    }

    @Test
    public void getAllInvokesWriteToFileWithRequestInfoObject() {

    }

    @Test
    public void getOneRequestResponseContentTypeIsJson() {

    }

    @Test
    public void getOneRequestReturnsCode404NotFoundIfThereWasNotSuchModel() {

    }

    @Test
    public void getLastRequestResponseContentTypeIsJson() {

    }

    @Test
    public void getLastRequestReturnsCode404NotFoundIfReadFromFileReturnsNull() {

    }
}