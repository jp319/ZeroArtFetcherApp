package com.jp319.ZeroArtFetcher.utils;

import java.util.List;

public class ZeroArtFetcherDownloader {
    //TODO: Make a Downloader (Should Download multiple files at once)
    private List<String> fileUrls;
    public ZeroArtFetcherDownloader() {
        this.fileUrls = List.of(
                "https://static.zerochan.net/Saber.%28Fate.stay.night%29.full.3662015.jpg",
                "https://static.zerochan.net/Fate.zero.full.3661729.jpg"
                // Add more URLs as needed
        );
        String downloadDir = new ZeroArtFetcherConfig().getSavedImageDirectory();
    }
}