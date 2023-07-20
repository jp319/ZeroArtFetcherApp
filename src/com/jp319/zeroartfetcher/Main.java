package com.jp319.zeroartfetcher;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            System.out.println
                    ("Enter a wallpaper ID (Separate it with comma if entering multiple IDs): [Enter X to exit]");
            System.out.print
                    ("> ");
            String idStr = scanner.next();
            if (!idStr.equals("X")) {
                System.out.println("Image link: " + getImage(idStr));
            } else {
                break;
            }
        }
        scanner.close();
    }
    private static String getImage (String id) throws IOException, InterruptedException {
        ZerochanSearcher zerochanSearcher = new ZerochanSearcher(id);
        return zerochanSearcher.getImgLink();
    }
}