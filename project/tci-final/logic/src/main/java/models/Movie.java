package models;

public class Movie implements IModel{
    private String name;
    private String genre;
    private String format;
    private Integer year;
    private String director;
    private String[] writers;
    private String[] stars;

    public Movie(){}

    public Movie(String name, String genre, String format, Integer year, String director, String[] writers, String[] stars){

        this.name = name;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.director = director;
        this.writers = writers;
        this.stars = stars;
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

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }

    public String[] getStars() {
        return stars;
    }

    public void setStars(String[] stars) {
        this.stars = stars;
    }
}
