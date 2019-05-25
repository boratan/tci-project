package services;

import models.EnrichedUrl;
import models.IModel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.swing.*;
import java.util.Set;
import java.util.concurrent.FutureTask;

public class ThreadService{
    private Set<FutureTask<IModel>> tasks;
    private Set<IModel> models;

    /**
     * Constructor of ThreadService class.
     * @param distinctUrls Distinct crawled URLs that need to be scraped.
     */
    public ThreadService(Set<EnrichedUrl> distinctUrls, Set<IModel> scrapedModels) {}

    /**
     * Starts scraping of the provided urls.
     */
    public Set<IModel> scrape(){ throw new NotImplementedException(); }

    /**
     * Checks which tasks are completed and adds the results to the return set.
     */
    public void checkFutureTasks(){}

    /**
     * Checks which tasks are completed and adds a single result to the return set.
     * @param type Type of IModel. Can be null.
     * @param details Specific detail that should be present in the model. Can be null.
     */
    public void checkFutureTasksForSpecificItem(String type, String details){}
}
