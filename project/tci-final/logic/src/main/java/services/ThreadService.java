package services;

import javafx.util.Pair;
import models.EnrichedUrl;
import models.IModel;
import models.RequestInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.util.Set;
import java.util.concurrent.FutureTask;

public class ThreadService{
    private Set<FutureTask<IModel>> tasks;
    private Set<IModel> models;
    private RequestInfo requestInfo;
    private Set<EnrichedUrl> exploredUrls;

    /**
     * Constructor of ThreadService class.
     * @param distinctUrls Distinct crawled URLs that need to be scraped.
     */
    public ThreadService(Set<EnrichedUrl> distinctUrls) {}

    /**
     * Starts scraping of the provided urls. Finally it sets the RequestInfo details with the size of exploredUrls.
     * @param type Type of IModel. Can be null.
     * @param details Specific detail that should be present in the model. Can be null.
     * @return Pair of the info for the request and the set of models scraped.
     */
    public Pair<RequestInfo, Set<IModel>> scrape(String type, String details){ throw new NotImplementedException(); }

    /**
     * Checks which tasks are completed and adds the results to the return set.
     */
    public void checkFutureTasks(){}

    /**
     * Checks which tasks are completed and adds a single result to the return set.
     * @param type Type of IModel.
     * @param details Specific detail that should be present in the model.
     */
    public void checkFutureTasksForSpecificItem(String type, String details){}
}
