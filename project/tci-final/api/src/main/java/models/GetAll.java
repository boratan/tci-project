package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GetAll extends BaseRequest {
    private List<Movie> movies;
    private List<Book> books;
    private List<Music> music;

    public GetAll() {
        this.movies = new ArrayList<>();
        this.books = new ArrayList<>();
        this.music = new ArrayList<>();
    }

    public GetAll(Set<IModel> models) {
        super();
        this.movies = new ArrayList<>();
        this.books = new ArrayList<>();
        this.music = new ArrayList<>();
        if (!models.isEmpty())
            this.splitListToModels(models);
        else throw new IllegalArgumentException();
    }

    private void splitListToModels(Set<IModel> models) {
        for (IModel m : models) {
            if (m instanceof Book)
                this.books.add((Book) m);
            else if (m instanceof Movie)
                this.movies.add((Movie) m);
            else if (m instanceof Music)
                this.music.add((Music) m);
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Music> getMusic() {
        return music;
    }
}
