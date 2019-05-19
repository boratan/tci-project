package models;

public class Music implements IModel{
    private String name;
    private String format;
    private String year;
    private String artist;

    public Music(){}

    public Music(String name, String format, String year, String artist){

        this.name = name;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
