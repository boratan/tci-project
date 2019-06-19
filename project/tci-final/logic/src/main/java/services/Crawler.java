package services;

import javafx.util.Pair;
import models.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class Crawler {
    private Set<EnrichedUrl> visited;
    private Set<IModel> visitedResults;
    private URL headURL;
    private int depth;
    private ThreadService threadService;

    /**
     * Constructor for Crawler
     */
    public Crawler(ThreadService ts) {
        this.visited = new HashSet<>();
        this.visitedResults = new HashSet<>();
        this.headURL = null;
        this.depth = 0;
        this.SetThreadService(ts);
    }

    public Set<EnrichedUrl> getVisited() {
        return visited;
    }

    /**
     * Gets the headUrl of the crawling
     *
     * @return the headUrl from which the crawling begins.
     * can be null before the beginning of the crawling.
     */
    public URL getHeadURL() {
        return headURL;
    }

    /**
     * Increments the depth of the crawler
     */
    private void incrementDepth() {
        this.depth++;
    }

    /**
     * Sets the Crawler's Thread service to the passed ThreadService as argument
     *
     * @param ts Thread Service to assign. Cannot be null.
     */
    private void SetThreadService(ThreadService ts) {
        if (ts != null) this.threadService = ts;
        else throw new IllegalArgumentException();
    }

    /**
     * Recursively crawls the given Set of URLs.
     * For each visitedUrl it starts a FutureTask for scraping.
     * When complete returns a Pair with the initial URL as Key and a Set<E> of IModels as Value.
     * If type and argument are given, after every iteration checks if the currently crawled URL contains the searched IModel.
     *
     * @param urls     the set of URLs that will be crawled
     * @param type     the type of IModel that will be searched. Can be null.
     * @param argument a keyword identifying the IModel which will be searched. Can be null.
     * @return A Pair of EnrichedUrl that has the headUrl as Key and the depth of search as Value,
     * and a set of discovered IModels during the search.
     */
    public Pair<EnrichedUrl, Set<IModel>> crawl(final Set<URL> urls, String type, String argument) {
        if (urls != null) {

            //Sets the head URL to the link on which the crawling has begun
            if (getHeadURL() == null && !urls.isEmpty())
                this.headURL = urls.iterator().next();

            //Clear urls from already crawled URLs
            clearRepeatingURLs(urls);

            if (urls.isEmpty()) {
                final boolean a = (type == null || type.equals(""));
                final boolean b = (argument == null || argument.equals(""));
                if (a && b) checkAddModels();
                return new Pair<>(new EnrichedUrl(getHeadURL(), this.depth), this.visitedResults);
            } else {

                final Set<URL> newURLs = new HashSet<>();

                try {
                    for (URL url : urls) {
                        if (url.getHost().equals(getHeadURL().getHost())) {

                            //Convert url to EnrichedUrl and start a scraping task on it
                            addAndScrapeURL(url, type, argument);

                            //Add all links present on url to newURLs
                            newURLs.addAll(getLinksOnURL(url));
                        }
                    }

                    //Check if any of the future tasks has returned a match of the search
                    if ((type != null && !type.equals("")) || (argument != null && !argument.equals(""))) {
                        Set<IModel> completed = this.threadService.checkFutureTasksForSpecificItem(type, argument);
                        if (completed != null && !completed.isEmpty()) {
                            return new Pair<>(new EnrichedUrl(getHeadURL(), this.depth), completed);
                        }
                    }

                    //Increments the depth of the search for the next iteration
                    incrementDepth();
                    //Begins the next iteration of crawling with the newly gathered URLs
                    crawl(newURLs, type, argument);

                } catch (final IOException | Error ignored) {
                }
            }
//            else{
//                    threadService.shutdownPool();
//                }
            return new Pair<>(new EnrichedUrl(getHeadURL(), this.depth), this.visitedResults);
        } else throw new IllegalArgumentException();
    }

    /**
     * Clears the passed Set of URLs from URLs that have already been crawled.
     *
     * @param urls the staged Set<URL> that will be cleared.
     */
    private void clearRepeatingURLs(final Set<URL> urls) {
        for (EnrichedUrl visitedURL : getVisited()) {
            urls.remove(visitedURL.getUrl());
        }
    }

    /**
     * Creates and adds the url as EnrichedUrl to local Set of visited EnrichedUrls
     * and starts a scraper task.
     *
     * @param url      url that will be converted to EnrichedUrl.
     *                 A scraper task on this URL is started.
     * @param type     the type of IModel that the scraper will look for. Can be null.
     * @param argument a keyword identifying the IModel that the scraper will look for. Can be null.
     */
    private void addAndScrapeURL(URL url, String type, String argument) {
        EnrichedUrl enrichedURL = new EnrichedUrl(url, this.depth);
        this.visited.add(enrichedURL);
        this.threadService.scrape(enrichedURL);
    }

    /**
     * Connects the targetURL and retrieves all links present on the page.
     *
     * @param targetURL the URL that will be browsed for links
     * @return a Set<URL> populated with all links present on the targetURL
     * @throws IOException
     */
    private Set<URL> getLinksOnURL(URL targetURL) throws IOException {
        final Document doc = Jsoup.connect(targetURL.toString()).get();
        final Elements linksOnPage = doc.select("a[href]");
        Set<URL> urls = new HashSet<>();

        for (final Element element : linksOnPage) {
            final String urlText = element.attr("abs:href");
            final URL discoveredURL = new URL(urlText);
            urls.add(discoveredURL);
        }
        return urls;
    }

    /**
     * Retrieves a Set<IModel> from all tasks from the Thread Service.
     */
    private void checkAddModels() {
        Set<IModel> completed = this.threadService.checkFutureTasks();
        this.visitedResults.addAll(completed);
    }
}
