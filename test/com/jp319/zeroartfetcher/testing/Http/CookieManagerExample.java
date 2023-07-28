package com.jp319.zeroartfetcher.testing.Http;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class CookieManagerExample {
	
	/** The Constant URL_STRING. */
	
	private final static String URL_STRING = "https://www.zerochan.net";
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception
	{
		CookieManager cookieManager = new CookieManager();
		
		CookieHandler.setDefault(cookieManager);
		
		URL url = new URL(URL_STRING);
		
		URLConnection connection = url.openConnection();
		
		connection.getContent();
		
		CookieStore cookieStore = cookieManager.getCookieStore();
		
		List<HttpCookie> cookieList = cookieStore.getCookies();
		
		// iterate HttpCookie object
		for (HttpCookie cookie : cookieList)
		{
			// gets domain set for the cookie
			System.out.println("Domain: " + cookie.getDomain());
			
			// gets max age of the cookie
			System.out.println("max age: " + cookie.getMaxAge());
			
			// gets name cookie
			System.out.println("name of cookie: " + cookie.getName());
			
			// gets path of the server
			System.out.println("server path: " + cookie.getPath());
			
			// gets boolean if cookie is being sent with secure protocol
			System.out.println("is cookie secure: " + cookie.getSecure());
			
			// gets the value of the cookie
			System.out.println("value of cookie: " + cookie.getValue());
			
			// gets the version of the protocol with which the given cookie is related.
			System.out.println("value of cookie: " + cookie.getVersion());
			
		}
		
	}
}