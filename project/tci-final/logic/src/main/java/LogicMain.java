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
     * Instantiates a Crawler and begins to crawl the provided URL.
     * When complete, returns a set of crawled URLs
     * @param headURL
     * @return
     */
    private Set<URL> crawlUrl(URL headURL){
        throw new NotImplementedException();
    }
}
