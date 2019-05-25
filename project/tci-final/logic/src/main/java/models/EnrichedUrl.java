package models;

import java.net.URL;

public class EnrichedUrl {
    private URL ulr;
    private Integer depth;


    public EnrichedUrl(URL ulr, Integer depth) {
        this.ulr = ulr;
        this.depth = depth;
    }

    public URL getUlr() {
        return ulr;
    }

    public void setUlr(URL ulr) {
        this.ulr = ulr;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }
}
