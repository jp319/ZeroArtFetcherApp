package com.jp319.zeroartfetcher;

import java.util.List;

public class ZerochanItem {
    private String id;
    private String full;
    private String width;
    private String height;
    private String size;
    private String primary;
    private String thumbnail;
    private String source;
    private String tag;
    private List<String> tags;
    public String getId() {
        return id;
    }
    public String getFull() {
        return full;
    }
    public String getWidth() {
        return width;
    }
    public String getHeight() {
        return height;
    }
    public String getSize() {
        return size;
    }
    public String getPrimary() {
        return primary;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public String getSource() {
        return source;
    }
    public String getTag() {
        return tag;
    }
    public List<String> getTags() {
        return tags;
    }
    public String joinTags(String delimiter) {
        return String.join(delimiter, tags);
    }
    public String getCommaSeparatedTags() {
        return joinTags(", ");
    }
    public String getSpaceSeparatedTags() {
        return joinTags(" ");
    }
    public String getNewlineSeparatedTags() {
        return joinTags("\n");
    }
}