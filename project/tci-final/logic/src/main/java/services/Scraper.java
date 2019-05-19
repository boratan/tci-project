import org.jsoup.nodes.Document;
package services;

/**
 * The class representing a scraper task that deals with a single url.
 */
public class Scraper extends NotifyingThread {

    /**
     * Constructor of Scraper class.
     * @param url The URL that needs to be scraped.
     */
    public Scraper(String url) {}

    /**
     * Starts the scraper task.
     */
    @Override
    public void startScraper(){}

    /**
     * Gets the data required for mapping the model and adds the mapped model to the repository of found models.
     * @param doc HTML of the page that is being scraped.
     */
    private void getModel(Document doc) {}
}
