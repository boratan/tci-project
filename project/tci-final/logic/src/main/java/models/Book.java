package models;

import java.util.List;

public class Book implements IModel {
    private String name;
    private String genre;
    private String format;
    private Integer year;
    private String authors;
    private String publisher;
    private String isbn;

    public Book() {
    }

    public Book(String name, String genre, String format, Integer year,
                String authors, String publisher, String isbn)
    {
        this.setName(name);
        this.setGenre(genre);
        this.setFormat(format);
        this.setYear(year);
        this.setAuthors(authors);
        this.setPublisher(publisher);
        this.setisbn(isbn);
    }

    public String getName() { return name; }

    public String getGenre() {
        return genre;
    }

    public String getFormat() {
        return format;
    }

    public Integer getYear() {
        return year;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() { return publisher; }

    public String getIsbn() { return isbn; }

    public void setName(String name) {
        if (name != null && !name.equals(""))
            this.name = name;
        else
            throw new IllegalArgumentException();
    }

    public void setGenre(String genre) {
        if (genre != null && !genre.equals(""))
            this.genre = genre;
        else
            throw new IllegalArgumentException();
    }

    public void setFormat(String format) {
        if (format != null && !format.equals(""))
            this.format = format;
        else
            throw new IllegalArgumentException();
    }

    public void setYear(Integer year) {
        if (year != null && year > 0)
            this.year = year;
        else
            throw new IllegalArgumentException();
    }

    public void setAuthors(String authors) {
        if (authors != null && authors != "")
            this.authors = authors;
        else
            throw new IllegalArgumentException();
    }

    public void setPublisher(String publisher) {
        if (publisher != null && !publisher.equals(""))
            this.publisher = publisher;
        else
            throw new IllegalArgumentException();
    }

    public void setisbn(String isbn) {
        String regex = "[0-9]*[-| ][0-9]*";
        if (isbn != null && isbn.matches(regex) && !isbn.equals(""))
            this.isbn = isbn;
        else
            throw new IllegalArgumentException();
    }
}
