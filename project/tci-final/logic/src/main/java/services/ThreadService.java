package services;

import models.EnrichedUrl;
import models.IModel;

import java.util.Set;
import java.util.concurrent.FutureTask;

public class ThreadService{
    private Set<FutureTask<IModel>> tasks;

    /**
     * Constructor of ThreadService class.
     * @param distinctUrls Distinct crawled URLs that need to be scraped.
     */
    public ThreadService(Set<EnrichedUrl> distinctUrls, Set<IModel> scrapedModels) {}

    /**
     * Starts scraping of the provided urls.
     */
    public void scrape(){}

    public void checkFutureTasks(){}
}
