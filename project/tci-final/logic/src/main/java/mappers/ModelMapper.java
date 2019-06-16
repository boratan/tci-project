package mappers;

import helper.ScrapedDataDoesNotContainIModelException;
import models.Book;
import models.IModel;
import models.Movie;
import models.Music;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModelMapper {

    /**
     * Checks the category of the data provided and calls a method to map
     * to a IModel instance accordingly.
     * Returns ScrapedDataDoesNotContainIModelException if no category is found.
     * @param data
     * @return
     */
    public IModel mapToModel(Element data) throws ScrapedDataDoesNotContainIModelException {
        if(data == null) {
            throw new IllegalArgumentException();
        }

        Elements th = data.select("th");
        Element category = th.get(0);

        if(category.text().equals("Category")) {
            Element td = category.siblingElements().first();

            switch (td.text().toLowerCase()) {
                case "movies":
                    return mapToMovie(data);
                case "books":
                    return mapToBook(data);
                case "music":
                    return mapToMusic(data);
                    default:
                        return null;
            }
        } else {
            throw new ScrapedDataDoesNotContainIModelException("Category is null");
        }
    }

    /**
     * Maps data from the scraper to Book model.
     * @param data
     * @return
     */
    private Book mapToBook(Element data) {
        String title = data.select("h1").first().text();
        String genre = "";
        String format = "";
        Integer year = 0;
        String authors = "";
        String publisher = "";
        String isnb = "";

        Elements elements = data.select("tr");

        for (Element el : elements) {
            if(!el.selectFirst("th").text().equals("Category")) {
                switch (el.selectFirst("th").text().toLowerCase()) {
                    case "genre":
                        genre = el.selectFirst("td").text();
                        break;
                    case "format":
                        format = el.selectFirst("td").text();
                        break;
                    case "year":
                        year = Integer.parseInt(el.selectFirst("td").text());
                        break;
                    case "authors":
                        authors = el.selectFirst("td").text();
                        break;
                    case "publisher":
                        publisher = el.selectFirst("td").text();
                        break;
                    case "isnb":
                        isnb = el.selectFirst("td").text();
                        break;
                }
            }
        }

        return new Book(title, genre, format, year, authors, publisher, isnb);
    }

    /**
     * Maps data from the scraper to Movie model.
     * Returns ScrapedDataDoesNotContainIModelException if data has not book.
     * @param data
     * @return
     */
    private Movie mapToMovie(Element data) {
        String name = data.select("h1").first().text();
        String genre = "";
        String format = "";
        Integer year = 0;
        String director = "";
        List<String> writers = new ArrayList<>();
        List<String> stars = new ArrayList<>();

        Elements elements = data.select("tr");

        for (Element el : elements) {
            if(!el.selectFirst("th").text().equals("Category")) {
                switch (el.selectFirst("th").text()) {
                    case "Genre":
                        genre = el.selectFirst("td").text();
                        break;
                    case "Format":
                        format = el.selectFirst("td").text();
                        break;
                    case "Year":
                        year = Integer.parseInt(el.selectFirst("td").text());
                        break;
                    case "Director":
                        director = el.selectFirst("td").text();
                        break;
                    case "Writers":
                        String writersCombined = el.selectFirst("td").text();
                        writers = Arrays.asList(writersCombined.trim().split(","));
                        break;
                    case "Stars":
                        String starsCombined = el.selectFirst("td").text();
                        stars = Arrays.asList(starsCombined.trim().split(","));
                        break;
                }
            }
        }

        return new Movie(name, genre, format, year, director, writers, stars);
    }

    /**
     * Maps data from the scraper to Music model.
     * Returns ScrapedDataDoesNotContainIModelException if data has not book.
     * @param data
     * @return
     */
    private Music mapToMusic(Element data) {
        String name = data.select("h1").first().text();
        String genre = "";
        String format = "";
        Integer year = 0;
        String artist = "";

        Elements elements = data.select("tr");

        for (Element el : elements) {
            if(!el.selectFirst("th").text().equals("Category")) {
                switch (el.selectFirst("th").text()) {
                    case "Genre":
                        genre = el.selectFirst("td").text();
                        break;
                    case "Format":
                        format = el.selectFirst("td").text();
                        break;
                    case "Year":
                        year = Integer.parseInt(el.selectFirst("td").text());
                        break;
                    case "Artist":
                        artist = el.selectFirst("td").text();
                        break;
                }
            }
        }

        return new Music(name, genre, format, year, artist);
    }
}
