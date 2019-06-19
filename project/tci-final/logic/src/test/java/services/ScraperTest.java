package services;

import mappers.ModelMapper;
import models.EnrichedUrl;
import models.IModel;
import models.Music;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import javax.jws.WebParam;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import static org.mockito.Mockito.*;

/**
 * Author: M. Andreeva
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Scraper.class)
public class ScraperTest {

    private Scraper scraper;
    private EnrichedUrl url;

    /**
     * setup
     */
    @Before
    public void setUp() {
        url = mock(EnrichedUrl.class);
        scraper = PowerMockito.spy(new Scraper(url));
    }

    /**
     * The test mocks the behaviour of url.getUrl method to return a url of the given website
     * which does not contain IModel. It creates a thread pool and starts a scraper task.
     * It then gets the result of the scraper task and asserts if its null.
     * Expected result is a null IModel.
     * @throws Exception
     */
    @Test
    public void checkCallMethodReturnsNullIfNoIModel() throws Exception {
        //arrange
        when(url.getUrl()).thenReturn(new URL("http://tci.hera.fhict.nl/catalog.php"));
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<IModel> task = new FutureTask<>(scraper);
        executorService.submit(task);

        //act
        IModel result = task.get();
        executorService.shutdown();

        //assert
        Assert.assertNull("Scraper returned IModel, when should be null", result);
    }

    /**
     * The test mocks the behaviour of url.getUrl method to return a url of the given website
     * which does contains IModel. It creates a thread pool and starts a scraper task.
     * It then gets the result of the scraper task and asserts if its null.
     * Expected result is a not null IModel.
     * @throws Exception
     */
    @Test
    public void checkCallMethodReturnsNotNullIModel() throws Exception {
        //arrange
        when(url.getUrl()).thenReturn(new URL("http://tci.hera.fhict.nl/details.php?id=102"));
        //PowerMockito.doReturn(model).when(mapper, "mapToModel", doc.getElementsByClass("media-details").first());
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<IModel> task = new FutureTask<>(scraper);
        executorService.submit(task);

        //act
        IModel result = task.get();
        executorService.shutdown();

        //assert
        Assert.assertNotNull("Scraper returned null, when music is present", result);
    }

    /**
     * The test mocks the behaviour of url.getUrl method to return a url of the given website
     * which does contains IModel. It creates a thread pool and starts a scraper task.
     * It then mocks a jsoup Document and its behaviour when getElementsByClass is called with
     * parameter "media-details". It then invokes the private method getIModel of the scraper and
     * verifies whether the getElementByClass method of the doc was called inside.
     * @throws Exception
     */
    @Test
    public void verifyThatGetIModelInvokedGetElementsByClassOfDocument() throws Exception {
        //arrange
        when(url.getUrl()).thenReturn(new URL("http://tci.hera.fhict.nl/details.php?id=102"));
        Document doc = mock(Document.class);
        Document realDoc = Jsoup.connect(url.getUrl().toString()).get();
        when(doc.getElementsByClass("media-details")).thenReturn(realDoc.getElementsByClass("media-details"));

        //act
        Whitebox.invokeMethod(scraper, "getIModel", doc);

        //assert
        verify(doc).getElementsByClass("media-details");
    }

    /**
     * The test mocks the behaviour of url.getUrl method to return a url of the given website
     * which does contains IModel. It creates a thread pool and starts a scraper task.
     * It then mocks a jsoup Document and its behaviour when getElementsByClass is called with
     * parameter "media-details". Then a ModelMapper is mocked with its behaviour for mapToModel
     * and replaced in the Scraper instance.
     * The test then invokes the private method getIModel of the scraper and
     * verifies whether the mapToModel method of the mapper was called inside.
     * @throws Exception
     */
    @Test
    public void verifyThatGetIModelInvokedMapToModelOfMapper() throws Exception {
        //arrange
        when(url.getUrl()).thenReturn(new URL("http://tci.hera.fhict.nl/details.php?id=102"));
        Document doc = mock(Document.class);
        Document realDoc = Jsoup.connect(url.getUrl().toString()).get();
        when(doc.getElementsByClass("media-details")).thenReturn(realDoc.getElementsByClass("media-details"));
        ModelMapper mapper = mock(ModelMapper.class);
        when(mapper.mapToModel(doc.getElementsByClass("media-details").first())).thenReturn(null);
        // try to substitude the mapper for the mocked one.
        MemberModifier
                .field(Scraper.class, "mapper").set(
                scraper , mapper);

        //act
        Whitebox.invokeMethod(scraper, "getIModel", doc);

        //assert
        verify(mapper).mapToModel(doc.getElementsByClass("media-details").first());
    }
}