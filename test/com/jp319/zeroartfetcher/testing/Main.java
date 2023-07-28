package com.jp319.zeroartfetcher.testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        String input = readJsonFileAsString("src/main/resources/data/sample.json"); // Replace this with your actual input string
        String output = removeDivSections(input);
        System.out.println(output);
    }

    public static String removeDivSections(String input) {
        Pattern pattern = Pattern.compile("<div.*?</div>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(input);
        return matcher.replaceAll("");
    }
    public static String readJsonFileAsString(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes);
    }
}
