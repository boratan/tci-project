package models;

public class RequestInfo implements IModel{
    private Integer id;
    private Integer time;
    private Integer pagesExplored;
    private Integer uniquePagesExplored;
    private Integer searchDepth;

    public RequestInfo(){}

    public RequestInfo(Integer pagesExplored, Integer uniquePagesExplored, Integer searchDepth){
        this.pagesExplored = pagesExplored;
        this.uniquePagesExplored = uniquePagesExplored;
        this.searchDepth = searchDepth;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        if (time > 0)
            this.time = time;
        else
            throw new IllegalArgumentException();
    }

    public Integer getPagesExplored() {
        return pagesExplored;
    }

    public void setPagesExplored(Integer pagesExplosed) {
        if (pagesExplosed > 0)
            this.pagesExplored = pagesExplosed;
        else
            throw new IllegalArgumentException();
    }

    public Integer getUniquePagesExplored() { return uniquePagesExplored; }

    public void setUniquePagesExplored(Integer uniquePagesExplored) {
        if (uniquePagesExplored > 0)
            this.uniquePagesExplored = uniquePagesExplored;
        else
            throw new IllegalArgumentException();
    }

    public Integer getSearchDepth() {
        return searchDepth;
    }

    public void setSearchDepth(Integer searchDepth) {
        if (searchDepth > 0)
            this.searchDepth = searchDepth;
        else
            throw new IllegalArgumentException();
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        if (id > 0)
            this.id = id;
        else
            throw new IllegalArgumentException();
    }
}
