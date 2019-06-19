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

/**
 * Author: B. Atanasov
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ThreadService.class)
public class ThreadServiceTest {
    private ThreadService threadService;
    private static Movie movie;
    private static Book book;
    private static Music music;

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
     * Creates the static content needed for all the tests.
     */
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
                "CD",
                2012,
                "Ludwig van Beethoven"
        );
    }

    /**
     * Creates a TheadService and calls the scrape method with a null as a url parameter, which throws an
     * IllegalArgumentException.
     */
    @Test
    public void checkThatScrapeMethodThrowsExceptionWhenUrlIsNull() {
        threadService = new ThreadService();

        exception.expect(IllegalArgumentException.class);
        threadService.scrape(null);
    }

    /**
     * Creates a TheadService and a mocked URL and calls the scrape method with the URL as a url parameter.
     * Shows that there is exactly one task created.
     */
    @Test
    public void checkThatScrapeMethodCreatesFutureTasksWhenUrlIsNotEmpty() {
        threadService = new ThreadService();
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        threadService.shutdownPool();

        Assert.assertEquals(1, threadService.getTasks().size());
    }

    /**
     * Creates a ThreadService and a mocked URL and calls the scrape method 3 times with the URL as a url parameter.
     * Shows that there is exactly 3 task created.
     */
    @Test
    public void checkThatScrapeMethodStartsExecutesFutureTasksForEachUrlOf3() {
        threadService = new ThreadService();
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        threadService.scrape(url);
        threadService.scrape(url);
        threadService.shutdownPool();

        Assert.assertEquals(3, threadService.getTasks().size());
    }

    /**
     * Creates a ThreadService and a mocked URL and calls the scrape method 3 times with the URL as a url parameter.
     * The results of the tasks created in the ThreadService are mocked and the checkFutureTasks method is called
     * to show that all of the mocked models are returned.
     */
    @Test
    public void checkFutureTasksMethodWhenTypeAndDetailsParametersAreNullReturnsListOfAllScrapedModels() throws Exception {
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

    /**
     * Creates a ThreadService and a mocked URL and calls the scrape method 3 times with the URL as a url parameter.
     * The results of the tasks created in the ThreadService are mocked and the checkFutureTasksForSpecificItem method
     * is called with parameters that point to a specific item to show that that specific item will be returned.
     */
    @Test
    public void checkFutureTasksForSpecificItemMethodWhenThereAreTypeAndDetailsReturnsSingleModel() throws Exception {
        threadService = PowerMockito.spy(new ThreadService());
        EnrichedUrl url = mock(EnrichedUrl.class);

        threadService.scrape(url);
        threadService.scrape(url);
        threadService.scrape(url);
        Set<FutureTask<IModel>> tasks = threadService.getTasks();
        PowerMockito.doReturn(movie).when(threadService, "getFromTask", tasks.toArray()[0]);
        PowerMockito.doReturn(book).when(threadService, "getFromTask", tasks.toArray()[1]);
        PowerMockito.doReturn(music).when(threadService, "getFromTask", tasks.toArray()[2]);
        Set<IModel> result = threadService
                .checkFutureTasksForSpecificItem("movie", "The Lord of the Rings: The Fellowship of the Ring");
        threadService.shutdownPool();

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(movie, result.toArray()[0]);
    }

    /**
     * Creates a ThreadService and a mocked URL and calls the scrape method 3 times with the URL as a url parameter.
     * The results of the tasks created in the ThreadService are mocked and the checkFutureTasksForSpecificItem method
     * is called with a detail parameter that is not contained in any model to show that no models will be returned.
     */
    @Test
    public void checkFutureTasksForSpecificItemMethodWhenDetailsIsNotFound() throws Exception {
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

    /**
     * Creates a ThreadService and a mocked URL and calls the scrape method with the URL as a url parameter.
     * The result of the task created in the ThreadService is mocked and the checkFutureTasksForSpecificItem method
     * is called to check that checkIfAnyFieldInAModelIsEqualToArgumentAndType method is invoked when both
     * type and details parameters are not null.
     */
    @Test
    public void invokedCheckIfAnyFieldInAModelIsEqualToArgumentWhenTypeAndDetailsParametersAreNotNull() throws Exception {
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

    /**
     * Creates a ThreadService and a mocked URL and calls the scrape method with the URL as a url parameter.
     * The result of the task created in the ThreadService is mocked and the checkFutureTasksForSpecificItem method
     * is called to check that checkIfAnyFieldInAModelIsEqualToArgument method is invoked when details
     * parameter is not null but type parameter is null.
     */
    @Test
    public void invokedCheckIfAnyFieldInAModelIsEqualToArgumentWhenOnlyDetailsParameterIsNotNull() throws Exception {
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

    /**
     * Creates a ThreadService and a mocked URL and calls the scrape method with the URL as a url parameter.
     * The result of the task created in the ThreadService is mocked and the checkFutureTasksForSpecificItem method
     * is called to check that checkIfAnyFieldInAModelIsEqualToType method is invoked when details
     * parameter is null but type parameter is not null.
     */
    @Test
    public void invokedCheckIfAnyFieldInAModelIsEqualToTypeWhenTypeParameterIsNotNull() throws Exception {
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