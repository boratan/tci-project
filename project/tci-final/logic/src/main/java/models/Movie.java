package models;

import java.util.List;

public class Movie implements IModel{
    private String name;
    private String genre;
    private String format;
    private Integer year;
    private String director;
    private List<String> writers;
    private List<String> stars;

    public Movie(){}

    public Movie(String name, String genre, String format, Integer year, String director, List<String> writers, List<String> stars){
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
        if(name == null) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if(genre == null) {
            throw new IllegalArgumentException();
        }
        this.genre = genre;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        if(format == null) {
            throw new IllegalArgumentException();
        }
        this.format = format;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        if(year == null) {
            throw new IllegalArgumentException();
        }
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        if(director == null) {
            throw new IllegalArgumentException();
        }
        this.director = director;
    }

    public List<String> getWriters() {
        return writers;
    }

    public void setWriters(List<String> writers) {
        if(writers == null) {
            throw new IllegalArgumentException();
        }
        this.writers = writers;
    }

    public List<String> getStars() {
        return stars;
    }

    public void setStars(List<String> stars) {
        if(stars == null) {
            throw new IllegalArgumentException();
        }
        this.stars = stars;
    }
}
