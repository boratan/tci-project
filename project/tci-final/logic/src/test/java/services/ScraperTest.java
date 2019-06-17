package services;

import mappers.ModelMapper;
import models.EnrichedUrl;
import models.IModel;
import models.Music;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.net.URL;

import static org.mockito.Mockito.*;

/**
 * Author: M. Andreeva
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Scraper.class)
public class ScraperTest {

    private Scraper scraper;
    private EnrichedUrl url;
    private ModelMapper mapper;


    @Before
    public void setUp() {
        //direct output
        url = mock(EnrichedUrl.class);
        mapper = mock(ModelMapper.class);
        scraper = PowerMockito.spy(new Scraper(url));
    }

    @Test
    public void CheckThatGetIModelMethodIsCalledWhenCallMethodIsExecuted() throws Exception {
        //arrange
        when(url.getUrl()).thenReturn(new URL("http://tci.hera.fhict.nl/"));
        Document doc = Jsoup.connect(url.getUrl().toString()).get();

        //act
        scraper.call();

        //assert
        PowerMockito.verifyPrivate(scraper).invoke("getIModel",new Object[] {doc, mapper});
    }

    @Test
    public void CheckGetIModelMethodReturnsNotNullIModel() throws Exception {
        //arrange
        Document doc = mock(Document.class);
        when(url.getUrl()).thenReturn(new URL("http://tci.hera.fhict.nl/details.php?id=102"));
        Music model = new Music("name", "genre", "format", 1999, "artist");
        PowerMockito.doReturn(model).when(scraper, "getIModel", new Object[] {doc, mapper});

        //act
        IModel result = scraper.call();

        //assert
        Assert.assertNotNull("Scraper returned null, when music is present", result);
    }

    @Test
    public void verifyThatGetIModelInvokedHasClassOfDocument() throws Exception {
        Document doc = mock(Document.class);
        when(url.getUrl()).thenReturn(new URL("http://tci.hera.fhict.nl/"));

        Whitebox.invokeMethod(scraper, "getIModel", doc);

        verify(doc).hasClass("media-details");
    }
}