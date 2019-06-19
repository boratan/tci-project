package main;

import org.javatuples.Pair;
import models.EnrichedUrl;
import models.IModel;
import models.RequestInfo;
import services.Crawler;
import services.ThreadService;
import java.net.URL;
import java.util.Collections;
import java.util.Set;

public class LogicMain {

    private ThreadService threadService;

    public LogicMain() {
        this.threadService = new ThreadService();
    }

    /**
     * Runs crawlAndScrapeUrls. When crawling is complete, creates a ThreadService, which starts scraper threads.
     * When scraping is complete, returns a Pair of Request info and Set of IModels to the API.
     *
     * @param startURL the URL that the crawling will begin from
     * @return A Pair of RequestInfo that has all visited URLs, the distinct URLs
     * and an EnrichedUrl headUrl as Key and the depth of search as Value,
     * and a set of discovered IModels during the search.
     */
    public Pair<RequestInfo, Set<IModel>> getAllFromUrl(final URL startURL) {
        return crawlAndScrapeUrls(startURL, null, null);
    }

    /**
     * Runs crawlAndScrapeUrls. When crawling is complete, creates a ThreadService, which starts scraper threads.
     * When scraping is complete, returns a Pair of Request info and Set of IModels containing the found model to the API.
     * If there was no found model, it throws IModelNotFoundException
     *
     * @param startURL the URL that the crawling will begin from
     * @param type     the type of IModel that will be searched. Can be null.
     * @param argument a keyword identifying the IModel which will be searched. Can be null.
     * @return A Pair of RequestInfo that has all visited URLs, the distinct URLs
     * and an EnrichedUrl headUrl as Key and the depth of search as Value,
     * and a set of discovered IModels during the search.
     */
    public Pair<RequestInfo, Set<IModel>> getOneFromUrl(final URL startURL, String type, String argument) {
        return crawlAndScrapeUrls(startURL, type, argument);
    }

    /**
     * Adds the given URL to a Set of a single instance of a URL
     *
     * @param startURL the URL that the crawling will begin from
     * @return A Pair of RequestInfo that has all visited URLs, the distinct URLs
     * and an EnrichedUrl headUrl as Key and the depth of search as Value,
     * and a set of discovered IModels during the search.
     */
    private Set<URL> headURL(final URL startURL) {

        return Collections.singleton(startURL);
    }

    /**
     * Instantiates a Crawler and begins to crawl the provided URL.
     * When complete, returns a set of crawled URLs
     *
     * @param startURL the URL that the crawling will begin from
     * @param type     the type of IModel that will be searched. Can be null.
     * @param argument a keyword identifying the IModel which will be searched. Can be null.
     * @return A Pair of RequestInfo that has all visited URLs, the distinct URLs
     * and an EnrichedUrl headUrl as Key and the depth of search as Value,
     * and a set of discovered IModels during the search.
     */
    private Pair<RequestInfo, Set<IModel>> crawlAndScrapeUrls(final URL startURL, String type, String argument) {
        if (startURL != null) {

            //Instantiates a crawler, starts a crawling on the startURL and returns the pair result
            Crawler crawler = new Crawler(threadService);
            Pair<EnrichedUrl, Set<IModel>> crawlerResult = crawler.crawl(headURL(startURL), type, argument);

            //Creates a RequestInfo based on the results from the crawl
            RequestInfo returnRequestInfo = new RequestInfo(
                    crawler.getVisited().size(),
                    (int) crawler.getVisited().stream().distinct().count(),
                    crawlerResult.getValue0().getDepth());

            return new Pair<>(returnRequestInfo, crawlerResult.getValue1());
        } else
            throw new IllegalArgumentException("Start URL cannot be empty!");
    }

}
