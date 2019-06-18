package models;

public class Music implements IModel{
    private String name;
    private String genre;
    private String format;
    private Integer year;
    private String artist;

    public Music(){}

    public Music(String name, String genre, String format, Integer year, String artist){
        this.name = name;
        this.genre = genre;
        this.format = format;
        this.year = year;
        this.artist = artist;
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

    public String getGenre() { return genre; }

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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        if(artist == null) {
            throw new IllegalArgumentException();
        }
        this.artist = artist;
    }
}
