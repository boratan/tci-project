package models;

public class Book implements IModel{
    private String name;
    private String genre;
    private String format;
    private Integer year;
    private String authors;
    private String publisher;
    private String isnb;

    public Book(){}

    public Book(String name, String genre, String format, Integer year, String authors, String publisher, String isnb){

        this.name = name;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.authors = authors;
        this.publisher = publisher;
        this.isnb = isnb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsnb() {
        return isnb;
    }

    public void setIsnb(String isnb) {
        this.isnb = isnb;
    }
}
