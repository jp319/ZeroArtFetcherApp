package com.jp319.ZeroArtFetcher.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZeroArtFetcherDownloadedImages {
    private final String imageDirectoryPath = new ZeroArtFetcherConfig().getSavedImageDirectory();
    private File[] getImageFiles() {
        File directory = new File(imageDirectoryPath);
        return directory.listFiles(new ImageFileFilter());
    }
    private String getRelativePath(String absolutePath) {
        Path baseDirectoryPath = Paths.get(imageDirectoryPath);
        Path filePath = Paths.get(absolutePath);
        Path relativePath = baseDirectoryPath.relativize(filePath);
        return relativePath.toString();
    }
    public String[] getImageFilePaths() {
        File[] imageFiles = getImageFiles();
        if (imageFiles == null) {
            return new String[0];
        }

        List<String> relativeFilePaths = new ArrayList<>();
        for (File imageFile : imageFiles) {
            String relativePath = getImageDirectoryPath() + getRelativePath(imageFile.getAbsolutePath());
            relativeFilePaths.add(relativePath);
        }

        return relativeFilePaths.toArray(new String[0]);
    }
    private static class ImageFileFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            return lowercaseName.endsWith(".jpg") || lowercaseName.endsWith(".png");
        }
    }
    public String getImageDirectoryPath() {
        return imageDirectoryPath;
    }

    public static void main(String[] args) {
        ZeroArtFetcherDownloadedImages zeroArtFetcherDownloadedImages =
                new ZeroArtFetcherDownloadedImages();
        System.out.println("fn Relative Image Path: "+Arrays.toString(zeroArtFetcherDownloadedImages.getImageFilePaths()));
        System.out.println("fn Image Directory Path: "+zeroArtFetcherDownloadedImages.getImageDirectoryPath());
    }
}