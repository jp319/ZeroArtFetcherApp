package com.jp319.zeroartfetcher.testing.Http;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.Arrays;

public class HttpClientExampleV2 {
	public static void main(String[] args) {
		try {
			BasicCookieStore cookieStore = new BasicCookieStore();
			CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
			
			HttpClientContext context = HttpClientContext.create();
			context.setCookieStore(cookieStore);
			
			String url = "https://www.zerochan.net/hentai?json&";
			String url2 = "https://www.zerochan.net/bleach?json&";
			HttpGet httpGet = new HttpGet(url2);
			
			System.out.println(httpGet.getURI().toURL());
			// Set other request headers
			httpGet.addHeader("User-Agent", "My AI learning app - Mad+Madness9798");
			httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
			httpGet.addHeader("Accept-Language", "en-US,en;q=0.5");
			httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
			httpGet.addHeader("DNT", "1");
			httpGet.addHeader("Connection", "keep-alive");
			httpGet.addHeader("Upgrade-Insecure-Requests", "1");
			httpGet.addHeader("Sec-Fetch-Dest", "document");
			httpGet.addHeader("Sec-Fetch-Mode", "navigate");
			httpGet.addHeader("Sec-Fetch-Site", "none");
			httpGet.addHeader("Sec-Fetch-User", "?1");
			httpGet.addHeader("Pragma", "no-cache");
			httpGet.addHeader("Cache-Control", "no-cache");
			
			// Set cookies
			String cookieValue =
					"guest_id=10110310; z_theme=13; z_id=1844774; z_hash=753583b5935217a66a7520a09fa8836a; mode=3; PHPSESSID=de2o9e0i70se13n8m1doknck09";
			httpGet.addHeader("Cookie", cookieValue);
			
			// Execute the request
			CloseableHttpResponse response = httpClient.execute(httpGet, context);
			HttpEntity entity = response.getEntity();
			
			System.out.println("Response code: "+ response.getStatusLine().getStatusCode());
			System.out.println(response.getFirstHeader("Content-Encoding").getValue());
			//System.out.println(Arrays.toString(response.getAllHeaders()));
			
			// Read the response content
			String content = EntityUtils.toString(entity);
			//System.out.println(content);
			
			// Release resources
			EntityUtils.consume(entity);
			response.close();
			httpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
