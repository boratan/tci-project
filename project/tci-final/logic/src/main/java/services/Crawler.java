package services;

import models.EnrichedUrl;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URL;
import java.util.Set;

public class Crawler {

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
    private Set<EnrichedUrl> crawl(final Set<URL> urls){
        throw new NotImplementedException();
    }
}
