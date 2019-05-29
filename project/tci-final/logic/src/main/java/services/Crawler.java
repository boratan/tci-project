package services;

import javafx.util.Pair;
import models.EnrichedUrl;
import models.IModel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URL;
import java.util.Set;

public class Crawler {
    private Set<EnrichedUrl> visited;
    private ThreadService ts;

    /**
     * Constructor for Crawler
     */
    private Crawler(){}

    /**
     * Recursively crawls the given Set of URLs.
     * When complete returns a Set of crawled URLs.
     * @param urls
     * @return
     */
    private Pair<EnrichedUrl, Set<IModel>> crawl(final Set<URL> urls){
        // for each crawl iteration
        // after adding the current urls to a list of visited
        // start a scraping task for each of them in the ThreadService
        // in the finally block of the crawl start new try-catch where the checking for finished FutureTasks will happen
        // in the finally block of the checking set the RequestInfo and return the Pair of RequestInfo and Set of IModel

        throw new NotImplementedException();
    }

    /**
     * Recursively crawls the given Set of URLs.
     * For each visitedUrl it starts a FutureTask for scraping.
     *
     * When complete returns a Set of crawled URLs.
     * @param urls
     * @return
     */
    private Pair<EnrichedUrl, Set<IModel>> crawl(final Set<URL> urls, String type, String argument){
        throw new NotImplementedException();
    }
}
