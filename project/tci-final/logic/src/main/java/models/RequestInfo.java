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
        this.time = time;
    }

    public Integer getPagesExplored() {
        return pagesExplored;
    }

    public void setPagesExplored(Integer pagesExplosed) {
        this.pagesExplored = pagesExplosed;
    }

    public Integer getUniquePagesExplored() { return uniquePagesExplored; }

    public void setUniquePagesExplored(Integer uniquePagesExplored) {
        this.uniquePagesExplored = uniquePagesExplored;
    }

    public Integer getSearchDepth() {
        return searchDepth;
    }

    public void setSearchDepth(Integer searchDepth) {
        this.searchDepth = searchDepth;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }
}
