package services;

import helper.ScrapedDataDoesNotContainIModelException;
import mappers.ModelMapper;
import models.EnrichedUrl;
import models.IModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.concurrent.Callable;

/**
 * The class representing a scraper task that deals with a single url.
 */
public class Scraper implements Callable<IModel> {

    private final EnrichedUrl enrichedUrl;
    private Document doc;
    private ModelMapper mapper;

    /**
     * Constructor of Scraper class.
     * @param url The URL that needs to be scraped.
     */
    public Scraper(EnrichedUrl url)
    {
        this.enrichedUrl = url;
        this.mapper = new ModelMapper();
    }

    /**
     * Calls all methods from the class and returns(if possible) a ready IModel from the provided url.
     */
    @Override
    public IModel call() throws Exception {
        String url = enrichedUrl.getUrl().toString();
        doc = Jsoup.connect(url).get();
        return this.getIModel(doc);
    }

    /**
     * Gets the data required for mapping the model and returns the mapped model.
     */
    private IModel getIModel(Document doc) {
        try {
            Element el = doc.getElementsByClass("media-details").first();
            if(el != null) {
                return mapper.mapToModel(el);
            }
            return null;
        } catch (ScrapedDataDoesNotContainIModelException e) {
            return null;
        }
    }
}
