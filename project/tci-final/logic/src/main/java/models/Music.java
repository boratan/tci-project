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
        this.name = name;
    }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
