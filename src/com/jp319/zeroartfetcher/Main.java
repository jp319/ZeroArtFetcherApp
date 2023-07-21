package com.jp319.zeroartfetcher;

import com.jp319.zeroartfetcher.core.ZerochanSearcher;
import com.jp319.zeroartfetcher.core.model.ZerochanItem;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            System.out.println
                    ("Enter a wallpaper ID (Separate it with comma if entering multiple IDs): [Enter X to exit]");
            System.out.println
                    ("Enter tags or filters or even both: ");
            System.out.print
                    ("> ");
            String idStr = scanner.next();
            System.out.print
                    ("> ");
            String filterStr = scanner.next();
            if (!idStr.equals("X")) {
                System.out.println(getThumbnails("Genshin+impact", "strict"));
            } else {
                break;
            }
        }
        scanner.close();
    }
    private static String getImage (String id) throws IOException, InterruptedException {
        ZerochanSearcher zerochanSearcher = new ZerochanSearcher(id);
        return zerochanSearcher.getImg();
    }
    private static String getThumbnails (String tags, String filters) throws IOException, InterruptedException {
        ZerochanSearcher zerochanSearcher = new ZerochanSearcher(tags, filters);
        StringBuilder thumbnails = new StringBuilder();
        int i=0;
        for (ZerochanItem item : zerochanSearcher.getThumbnails()) {
            thumbnails.append("Thumbnail(").append(i++).append("): ").append(item.getThumbnail()).append(",\n");
        }
        return thumbnails.toString();
    }
}