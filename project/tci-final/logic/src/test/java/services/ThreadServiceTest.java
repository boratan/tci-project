package services;

import models.*;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.FutureTask;

import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ThreadService.class)
public class ThreadServiceTest {
    private ThreadService threadService;
    private static Movie movie;
    private static Book book;
    private static Music music;

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

    @BeforeClass
    public static void setUp(){
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
    }

    @Test
    public void CheckThatScrapeMethodThrowsExceptionWhenUrlIsNull() {
        threadService = new ThreadService();

        exception.expect(IllegalArgumentException.class);
        threadService.scrape(null);
    }

    @Test
    public void CheckThatScrapeMethodCreatesFutureTasksWhenUrlIsNotEmpty() {
        threadService = new ThreadService();
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        threadService.shutdownPool();

        Assert.assertEquals(1, threadService.getTasks().size());
    }

    @Test
    public void CheckThatScrapeMethodStartsExecutesFutureTasksForEachUrlOf3() {
        threadService = new ThreadService();
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        threadService.scrape(url);
        threadService.scrape(url);
        threadService.shutdownPool();

        Assert.assertEquals(3, threadService.getTasks().size());
    }

    @Test
    public void CheckFutureTasksMethodWhenTypeAndDetailsParametersAreNullReturnsListOfAllScrapedModels() throws Exception {
        threadService = PowerMockito.spy(new ThreadService());
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        threadService.scrape(url);
        threadService.scrape(url);
        Set<FutureTask<IModel>> tasks = threadService.getTasks();
        PowerMockito.doReturn(movie).when(threadService, "getFromTask", tasks.toArray()[0]);
        PowerMockito.doReturn(book).when(threadService, "getFromTask", tasks.toArray()[1]);
        PowerMockito.doReturn(music).when(threadService, "getFromTask", tasks.toArray()[2]);
        Set<IModel> result = threadService.checkFutureTasks();
        threadService.shutdownPool();

        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.contains(movie));
        Assert.assertTrue(result.contains(book));
        Assert.assertTrue(result.contains(music));
    }

    @Test
    public void CheckFutureTasksForSpecificItemMethodWhenThereAreTypeAndDetailsReturnsSingleModel() throws Exception {
        threadService = PowerMockito.spy(new ThreadService());
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        threadService.scrape(url);
        threadService.scrape(url);
        Set<FutureTask<IModel>> tasks = threadService.getTasks();
        PowerMockito.doReturn(movie).when(threadService, "getFromTask", tasks.toArray()[0]);
        PowerMockito.doReturn(book).when(threadService, "getFromTask", tasks.toArray()[1]);
        PowerMockito.doReturn(music).when(threadService, "getFromTask", tasks.toArray()[2]);
        Set<IModel> result = threadService.checkFutureTasksForSpecificItem("movie", "The Lord of the Rings: The Fellowship of the Ring");
        threadService.shutdownPool();

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(movie, result.toArray()[0]);
    }

    @Test
    public void CheckFutureTasksForSpecificItemMethodWhenDetailsIsNotFound() throws Exception {
        threadService = PowerMockito.spy(new ThreadService());
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        threadService.scrape(url);
        threadService.scrape(url);
        Set<FutureTask<IModel>> tasks = threadService.getTasks();
        PowerMockito.doReturn(movie).when(threadService, "getFromTask", tasks.toArray()[0]);
        PowerMockito.doReturn(book).when(threadService, "getFromTask", tasks.toArray()[1]);
        PowerMockito.doReturn(music).when(threadService, "getFromTask", tasks.toArray()[2]);
        Set<IModel> result = threadService.checkFutureTasksForSpecificItem("movie", "asdada");
        threadService.shutdownPool();

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void InvokedCheckIfAnyFieldInAModelIsEqualToArgumentWhenTypeAndDetailsParametersAreNotNull() throws Exception {
        threadService = PowerMockito.spy(new ThreadService());
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        Set<FutureTask<IModel>> tasks = threadService.getTasks();
        PowerMockito.doReturn(movie).when(threadService, "getFromTask", tasks.toArray()[0]);
        threadService.checkFutureTasksForSpecificItem("movie", "Drama");
        threadService.shutdownPool();

        PowerMockito.verifyPrivate(threadService)
                .invoke("checkIfAnyFieldInAModelIsEqualToArgumentAndType", new Object[]{movie, "Drama", "movie"});
    }

    @Test
    public void InvokedCheckIfAnyFieldInAModelIsEqualToArgumentWhenDetailsParameterIsNotNull() throws Exception {
        threadService = PowerMockito.spy(new ThreadService());
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        Set<FutureTask<IModel>> tasks = threadService.getTasks();
        PowerMockito.doReturn(movie).when(threadService, "getFromTask", tasks.toArray()[0]);
        threadService.checkFutureTasksForSpecificItem(null, "Drama");
        threadService.shutdownPool();

        PowerMockito.verifyPrivate(threadService)
                .invoke("checkIfAnyFieldInAModelIsEqualToArgument", new Object[]{movie, "Drama"});
    }

    @Test
    public void InvokedCheckIfAnyFieldInAModelIsEqualToTypeWhenTypeParameterIsNotNull() throws Exception {
        threadService = PowerMockito.spy(new ThreadService());
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        Set<FutureTask<IModel>> tasks = threadService.getTasks();
        PowerMockito.doReturn(movie).when(threadService, "getFromTask", tasks.toArray()[0]);
        threadService.checkFutureTasksForSpecificItem("movie", null);
        threadService.shutdownPool();

        PowerMockito.verifyPrivate(threadService)
                .invoke("checkIfAnyFieldInAModelIsEqualToType", new Object[]{movie, "movie"});
    }
}