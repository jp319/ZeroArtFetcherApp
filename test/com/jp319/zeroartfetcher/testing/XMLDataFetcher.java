package com.jp319.zeroartfetcher.testing;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class XMLDataFetcher {

    public static void main(String[] args) {
        String url = "https://www.zerochan.net/Sex?s=id&xml"; // Replace with your XML URL

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);

            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    HttpEntity entity = response.getEntity();
                    String xmlResponse = EntityUtils.toString(entity);

                    // Now you have the XML data in the xmlResponse string
                    System.out.println(xmlResponse);
                } else {
                    System.err.println("Failed to fetch XML data. Status code: " + statusCode);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
