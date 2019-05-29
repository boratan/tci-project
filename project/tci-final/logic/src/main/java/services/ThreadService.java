package services;

import javafx.util.Pair;
import models.EnrichedUrl;
import models.IModel;
import models.RequestInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class ThreadService{
    private Set<FutureTask<IModel>> tasks;
    private Set<IModel> models;
    private RequestInfo requestInfo;
    private Set<EnrichedUrl> exploredUrls;
    private ExecutorService threadPool;

    /**
     * Constructor of ThreadService class.
     */
    public ThreadService() {
        //create new thread pool of fixed size
    }

    /**
     * Starts scraping of the provided url by creating a new Scraper and future task.
     * It then adds the future task to the thread pool and list of future tasks.
     * @param urlToScrape URL that need to be scraped.
     * @param type Type of IModel. Can be null.
     * @param details Specific detail that should be present in the model. Can be null.
     * @return Pair of the info for the request and the set of models scraped.
     */
    public void scrape(EnrichedUrl urlToScrape, String type, String details){

        throw new NotImplementedException();
    }

    /**
     * Checks which tasks are completed and adds the results to the return set.
     * @return The set of IModel found in the whole website.
     */
    public Set<IModel> checkFutureTasks(){ throw new NotImplementedException(); }

    /**
     * Checks which tasks are completed and adds a single result to the return set.
     * @param type Type of IModel. Can be null.
     * @param details Specific detail that should be present in the model. Can be null.
     * @return The set of the first IModel found that meets the criteria provided.
     */
    public Set<IModel> checkFutureTasksForSpecificItem(String type, String details){ throw new NotImplementedException(); }

    /**
     * Getter that will be used to return the already explored urls to the crawler,
     * so it can set the explored urls in the RequestInfo data.
     * @return
     */
    public Set<EnrichedUrl> getExploredUrls() {
        return exploredUrls;
    }
}
