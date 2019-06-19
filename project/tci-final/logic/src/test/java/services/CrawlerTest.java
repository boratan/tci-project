package services;

import models.EnrichedUrl;
import models.IModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.mockito.Mock;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 * Test class for crawler
 */
public class CrawlerTest {

    private Crawler crawler;
    private Set<URL> urls;
    private URL url;
    private String type;
    private String argument;

    /**
     * Mocking
     */
    @Mock
    ThreadService ts = mock(ThreadService.class);

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
    public final ExpectedException exception = ExpectedException.none();

    /**
     * Rule
     */
    @Rule
    public TestRule globalTimeout = Timeout.seconds(7);

    /**
     * setup
     */
    @Before
    public void setUp() throws MalformedURLException {
        crawler = new Crawler(ts);
        urls = new HashSet<>();
        url = new URL("http://tci.hera.fhict.nl/");
        type = "type";
        argument = "argument";
        urls.add(url);
    }

    /**
     * When the constructor is called with argument null,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void setThreadServiceThrowsIllegalArgumentExceptionIfPassedArgumentIsNull(){
        exception.expect(IllegalArgumentException.class);
        Crawler testCrawler = new Crawler(null);
    }

    /**
     * When the constructor is called with empty Set<URL> as urls argument,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void crawlThrowsIllegalArgumentExceptionWhenAnEmptySetOfUrlsIsPassedAsUrlsArgument(){
        exception.expect(IllegalArgumentException.class);
        Set<URL> emptySet = Collections.emptySet();
        crawler.crawl(emptySet,null,null);
    }

    /**
     * When crawl() is invoked, the headUrl is not null.
     */
    @Test
    public void headURLIsNotNullAfterCallingCrawlMethod() {
        Set<URL> urls = new HashSet<>();
        try {
            URL url = new URL("http://tci.hera.fhict.nl");
            urls.add(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        crawler.crawl(urls, null, null);
        Assert.assertNotNull(crawler.getHeadURL());
    }

    /**
     * When crawl() is invoked, the headUrl is set to expected value.
     */
    @Test
    public void headURLIsSetToExpectedValueAfterCallingCrawlMethod() {
        crawler.crawl(urls, null, null);
        Assert.assertEquals(url, crawler.getHeadURL());
    }

    /**
     * When crawl() is invoked with legal arguments it returns
     * a pair of a legal Enriched Url and empty or populated Set<IModel>.
     */
    @Test
    public void afterCallingCrawlMethodWithLegalUrlArgumentItReturnsPairOfLegalEnrichedUrlAndSet() {
        Map.Entry<EnrichedUrl, Set<IModel>> pair = crawler.crawl(urls, null, null);
        assert pair != null;
    }

    /**
     * When crawl() is invoked with legal arguments
     * the depth of the crawl is incremented.
     */
    @Test
    public void afterCallingCrawlMethodWithLegalUrlArgumentWithChildLinksDepthIncreases(){
        Map.Entry<EnrichedUrl, Set<IModel>> pair = crawler.crawl(urls, null, null);
        Assert.assertNotEquals(0, (int)pair.getKey().getDepth());
    }

    /**
     * Verifies that ThreadService.scrape() is called correctly during the crawling process.
     */
    @Test
    public void afterCallingCrawlMethodWithLegalUrlScrapeIsCalled(){
        Map.Entry<EnrichedUrl, Set<IModel>> pair = crawler.crawl(urls, null, null);
        verify(ts).scrape(crawler.getVisited().iterator().next());
    }

    /**
     * Verifies that ThreadService.checkFutureTasks()
     * is called only once during the crawling process.
     */
    @Test
    public void afterCallingCrawlMethodWithLegalUrlCheckFutureTasksIsCalledOnlyOnce(){
        Map.Entry<EnrichedUrl, Set<IModel>> pair = crawler.crawl(urls, null, null);
        verify(ts, times(1)).checkFutureTasks();
    }

    /**
     * Verifies that ThreadService.checkFutureTasksForSpecificItem()
     * is called at least once during the crawling process.
     */
    @Test
    public void afterCallingCrawlMethodWithLegalUrlTypeAndArgumentCheckFutureTasksForSpecificItemIsCalledAtLeastOnce(){
        Map.Entry<EnrichedUrl, Set<IModel>> pair = crawler.crawl(urls, type, argument);
        verify(ts, atLeastOnce()).checkFutureTasksForSpecificItem(type,argument);
    }
}