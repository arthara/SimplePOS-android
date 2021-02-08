package com.simple.pos.shared.apiresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIResponseCollection<T> {
    @SerializedName("data")
    @Expose
    private T data = null;
    @SerializedName("links")
    @Expose
    private Link links;
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Link getLinks() {
        return links;
    }

    public void setLinks(Link links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
