package services;

import models.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
    private Movie movie;
    private Book book;
    private Music music;

    @Before
    public void setUp(){
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

    @Test (expected = IllegalArgumentException.class)
    public void CheckThatScrapeMethodThrowsExceptionWhenUrlIsNull() {
        threadService = new ThreadService();

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