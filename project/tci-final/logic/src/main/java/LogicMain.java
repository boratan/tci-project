import models.EnrichedUrl;
import models.IModel;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

public class LogicMain {

    /**
     * Runs crawlUrl. When crawling is complete, creates a ThreadService, which starts scraper threads.
     * When scraping is complete, sends a Set of IModels to the API.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

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
    private void crawlUrls(URL headURL){
        throw new NotImplementedException();
    }

    /**
     * Instantiates a TreadService and starts the scraping for all distinct URLs.
     * @param distinctUrls The distinct crawled URLs that should be scraped.
     */
    private void scrapeUrls(Set<EnrichedUrl> distinctUrls){ throw new NotImplementedException(); }
}
