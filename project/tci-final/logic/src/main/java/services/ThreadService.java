package services;

import java.net.URL;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class ThreadService {

    /**
     * Constructor of ThreadService class.
     * @param urls All crawler URLs that need to be scraped.
     * @param maxNumberOfThreads The maximum number of threads that can run at the same time.
     */
    public ThreadService(Set<URL> urls, int maxNumberOfThreads) {}

    /**
     * Starts scraping of the provided urls.
     */
    public void scrape(){}

    /**
     * Invoked as the scraping is done. Stops the tread pool.
     */
    protected void scrapingCompleted(){}

    /**
     * This method is invoced by any thread that is about to die.
     * @param thread
     */
    @Override
    public void notifyOfThreadComplete(Thread thread){}
}
