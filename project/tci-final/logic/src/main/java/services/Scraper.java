package services;

import models.EnrichedUrl;
import org.jsoup.nodes.Document;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The class representing a scraper task that deals with a single url.
 */
public class Scraper implements Runnable {

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


    @Override
    public void run()
    {
        try {
            // sleep for user given millisecond
            // before checking again
            Thread.sleep(waitTime);

            // return current thread name
            System.out.println(Thread
                    .currentThread()
                    .getName());
        }

        catch (InterruptedException ex) {
            Logger
                    .getLogger(Scraper.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the data required for mapping the model and adds the mapped model to the repository of found models.
     * @param doc HTML of the page that is being scraped.
     */
    private void getModel(Document doc) {}
}
