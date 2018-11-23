package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	
	//1. GET method without Header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient = HttpClients.createDefault(); //make the client connection
		HttpGet httpget = new HttpGet(url); //http get request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); //hit the GET url and get response in this object
		
		return closeableHttpResponse;

	}
	
	//2. GET method with Header (pass one more HashMap Object, whcih is key value pair in method)
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpClient = HttpClients.createDefault(); //make the client connection
		HttpGet httpget = new HttpGet(url); //http get request
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()){
			httpget.addHeader(entry.getKey(), entry.getValue());
		}
		// Now variable httpget has url as well as header also in it. 
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget); //hit the GET url and Header and get response in this object
		
		return closeableHttpResponse;

	}
	
	//3. POST method 
	public static CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws UnsupportedEncodingException, ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault(); //make client connection
		HttpPost httppost = new HttpPost(url); //http post request
		
		httppost.setEntity(new StringEntity(entityString)); // for Payload
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()){
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		// Now variable httppost has url, Payload as well as header also in it. 	
		
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httppost); ////hit the POST url and Header and get response in this object
		
		return closeableHttpResponse;
	}
}
