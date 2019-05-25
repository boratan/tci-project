package services;

import models.EnrichedUrl;
import models.IModel;
import org.jsoup.nodes.Document;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.concurrent.Callable;

/**
 * The class representing a scraper task that deals with a single url.
 */
public class Scraper implements Callable<IModel> {

    private final long waitTime;
    private final EnrichedUrl enrichedUrl;

    /**
     * Constructor of Scraper class.
     * @param url The URL that needs to be scraped.
     */
    public Scraper(EnrichedUrl url, int timeInMillis)
    {
        this.waitTime = timeInMillis;
        this.enrichedUrl = url;
    }

    /**
     * Calls all methods from the class and returns(if possible) a ready IModel from the provided url
     */
    @Override
    public IModel call() throws Exception {
        throw new NotImplementedException();
    }

    /**
     * Gets the data required for mapping the model and adds the mapped model to the repository of found models.
     * @param doc HTML of the page that is being scraped.
     */
    private IModel getIModel(Document doc) {
        throw new NotImplementedException();
    }

}
