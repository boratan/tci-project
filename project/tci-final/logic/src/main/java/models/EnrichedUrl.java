package models;

import java.net.URL;

public class EnrichedUrl {
    private URL url;
    private Integer depth;

    public EnrichedUrl(URL url, Integer depth) {
        this.url = url;
        this.depth = depth;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        if(url == null) {
            throw new IllegalArgumentException("URL cannot be null!");
        }
        this.url = url;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        if(depth == null) {
            throw new IllegalArgumentException("Search depth cannot be null!");
        }
        this.depth = depth;
    }
}
