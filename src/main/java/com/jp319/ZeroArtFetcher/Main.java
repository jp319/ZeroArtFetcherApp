package com.jp319.ZeroArtFetcher;

import com.jp319.ZeroArtFetcher.controllers.ZerochanSearcherOnline;
import com.jp319.ZeroArtFetcher.models.ZerochanItem;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Retrieving an individual image
        System.out.println("Individual Image Retriever Test");
         System.out.println("Image: " + getImage("3662015"));
         System.out.println("Image: " + getImage("3661729"));
        // Retrieving multiple images (Thumbnails)
        String tags = "Genshin+Impact";
        String filters = "Strict&p=2&l=200";
        System.out.println("\nMultiple Image Retriever Test");
        System.out.println(
                "Search: " +
                        "Tag(s) = "+tags+" & " +
                        "Filter(s) = "+filters+"\n" +
                getThumbnails(tags, filters) +
                "=====================================");
        tags = "Lumine,Flower";
        filters = "Strict&p=2&l=200";
        System.out.println(
                "Search: " +
                        "Tag(s) = "+tags+" & " +
                        "Filter(s) = "+filters+"\n" +
                        getThumbnails(tags, filters) +
                        "=====================================");

    }
    // Working
    private static String getImage (String id) {
        ZerochanSearcherOnline zerochanSearcherOnline = new ZerochanSearcherOnline(id);
        try {
            return zerochanSearcherOnline.getImg();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    // Working
    private static String getThumbnails (String tags, String filters) {
        ZerochanSearcherOnline zerochanSearcherOnline = new ZerochanSearcherOnline(tags, filters);
        StringBuilder thumbnails = new StringBuilder();
        int count = 1;
        try {
            for (ZerochanItem thumbnail : zerochanSearcherOnline.getThumbnails()) {
                thumbnails.append("Thumbnail (").append(count++).append("):");
                thumbnails.append(thumbnail.getThumbnail()).append(",\n");
            }
            thumbnails.deleteCharAt(thumbnails.length() - 2);
            return thumbnails.toString();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
