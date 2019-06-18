package main;

import javafx.util.Pair;
import main.LogicMain;
import models.IModel;
import models.RequestInfo;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.support.membermodification.MemberModifier;
import org.powermock.core.classloader.annotations.PrepareForTest;
import services.ThreadService;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
@PrepareForTest(LogicMain.class)
public class LogicMainTest {

    private LogicMain logicMain;
    private Set<URL> urls;
    private URL url;
    private String type;
    private String argument;


    @Mock
    ThreadService threadService;

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
    public void setUp() throws Exception {
        logicMain = new LogicMain();
        urls = new HashSet<>();
        url = new URL("http://tci.hera.fhict.nl/");
        type = "type";
        urls.add(url);
        argument = "argument";
    }

    /**
     * When getAllFromUrl is called with argument null,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void getAllFromUrlReturnsIllegalArgumentExceptionIfUrlIsNull() {
        exception.expect(IllegalArgumentException.class);
        logicMain.getAllFromUrl(null);
    }

    /**
     * When the getAllFromUrl is called with a legal URL argument,
     * does NOT return null
     */
    @Test
    public void getAllFromUrlInvokesCrawlAndScrapeMethodIfUrlIsNotNull() throws IllegalAccessException {
        MemberModifier.field(LogicMain.class, "threadService").set(logicMain, threadService);
        Pair<RequestInfo, Set<IModel>> pair = logicMain.getAllFromUrl(url);
        assert pair != null;
    }

    /**
     * When the getAllFromUrl is called with a legal URL, type and argument,
     * does NOT return null
     */
    @Test
    public void getAllFromUrlReturnsNotNullPairIfUrlIsNotNull() throws IllegalAccessException {
        MemberModifier.field(LogicMain.class, "threadService").set(logicMain, threadService);
        Pair<RequestInfo, Set<IModel>> pair = logicMain.getAllFromUrl(url);
        assert pair != null;
    }

    /**
     * When the getAllFromUrl is called with a legal URL, type and argument,
     * does NOT return a pair with Request info null
     */
    @Test
    public void getAllFromUrlReturnsPairWithNotNullRequestInfoIfUrlIsNotNull() throws IllegalAccessException {
        MemberModifier.field(LogicMain.class, "threadService").set(logicMain, threadService);
        Pair<RequestInfo, Set<IModel>> pair = logicMain.getAllFromUrl(url);
        assert pair.getKey() != null;
    }

    /**
     * When the getAllFromUrl is called with a legal URL, type and argument,
     * does NOT return a pair with Set<IModel> null
     */
    @Test
    public void getAllFromUrlReturnsPairWithNotNullSetOfIModelsIfUrlIsNotNull() throws IllegalAccessException {
        MemberModifier.field(LogicMain.class, "threadService").set(logicMain, threadService);
        Pair<RequestInfo, Set<IModel>> pair = logicMain.getAllFromUrl(url);
        assert pair.getValue() != null;
    }

    /**
     * When getAllFromUrl is called with argument url, type and arguments null,
     * Illegal Argument Exception is thrown.
     */
    @Test
    public void getOneFromUrlReturnsIllegalArgumentExceptionIfUrlIsNull() {
        exception.expect(IllegalArgumentException.class);
        logicMain.getOneFromUrl(null, null, null);
    }

    /**
     * When the getOneFromUrl is called with a legal URL, type and argument,
     * does NOT return null
     */
    @Test
    public void getOneFromUrlReturnsNotNullPairIfUrlTypeAndArgumentAreNotNull() throws IllegalAccessException {
//        ModelMapper mapper = mock(ModelMapper.class);
//        when(mapper.mapToModel(doc.getElementsByClass("media-details").first())).thenReturn(null);
//
//        // try to substitude the mapper for the mocked one.
//        MemberModifier.field(Scraper.class, "mapper").set(scraper , mapper);
//        whenNew(Crawler.class).withAnyArguments().thenReturn(crawler);
//        whenNew(ThreadService.class).withAnyArguments().thenReturn(threadService);
//        when(crawler.crawl(urls, type, argument)).thenReturn(new Pair<>(null, null));

        MemberModifier.field(LogicMain.class, "threadService").set(logicMain, threadService);
        Pair<RequestInfo, Set<IModel>> pair = logicMain.getOneFromUrl(url, type, argument);
        assert pair != null;
    }

    /**
     * When the getOneFromUrl is called with a legal URL, type and argument,
     * does NOT return a pair with Request info null
     */
    @Test
    public void getOneFromUrlReturnsPairWithNotNullRequestInfoIfUrlTypeAndArgumentAreNotNull() throws IllegalAccessException {
        MemberModifier.field(LogicMain.class, "threadService").set(logicMain, threadService);
        Pair<RequestInfo, Set<IModel>> pair = logicMain.getOneFromUrl(url, type, argument);
        assert pair.getKey() != null;
    }

    /**
     * When the getOneFromUrl is called with a legal URL, type and argument,
     * does NOT return a pair with Set<IModel> null
     */
    @Test
    public void getOneFromUrlReturnsPairWithNotNullSetOfIModelsIfUrlTypeAndArgumentAreNotNull() throws IllegalAccessException {
        MemberModifier.field(LogicMain.class, "threadService").set(logicMain, threadService);
        Pair<RequestInfo, Set<IModel>> pair = logicMain.getOneFromUrl(url, type, argument);
        assert pair.getValue() != null;
    }
}