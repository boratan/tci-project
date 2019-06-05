import helper.IModelNotFoundException;
import javafx.util.Pair;
import models.EnrichedUrl;
import models.IModel;
import models.RequestInfo;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class LogicMain {

    /**
     * Runs crawlUrl. When crawling is complete, creates a ThreadService, which starts scraper threads.
     * When scraping is complete, returns a Pair of Request info and Set of IModels to the API.
     * @param startURL
     * @return
     */
    public Pair<RequestInfo, Set<IModel>> getAllFromUrl(final URL startURL) {
        throw new NotImplementedException();
    }

    /**
     * Runs crawlUrl. When crawling is complete, creates a ThreadService, which starts scraper threads.
     * When scraping is complete, returns a Pair of Request info and Set of IModels containing the found model to the API.
     * If there was no found model, it throws IModelNotFoundException
     * @param startURL
     * @return
     */
    public Pair<RequestInfo, Set<IModel>> getOneFromUrl(final URL startURL, String type, String argument) {
        throw new NotImplementedException();
    }

    /**
     * Adds the given URL to a Set of a single instance of a URL
     * @param startURL
     * @return
     */
    private Set<URL> headURL(final URL startURL){
        throw new NotImplementedException();
    }

    /**
     * Instantiates a Crawler and begins to crawl the provided URL.
     * When complete, returns a set of crawled URLs
     * @param headURL
     * @return
     */
    private Pair<RequestInfo, Set<IModel>> crawlAndScrapeUrls(URL headURL){
        throw new NotImplementedException();
    }

    /**
     * Instantiates a TreadService and starts the scraping for all distinct URLs.
     * @param distinctUrls The distinct crawled URLs that should be scraped.
     */
    private Pair<RequestInfo, Set<IModel>> scrapeUrls(Set<EnrichedUrl> distinctUrls){ throw new NotImplementedException(); }
}
