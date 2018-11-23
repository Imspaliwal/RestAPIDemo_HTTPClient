package com.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BaseClass;
import com.qa.client.RestClient;
import com.qa.data.UserData;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PostAPITest extends BaseClass{
	BaseClass baseClass;
	String serviceURL;
	String apiURL;
	String url;
	
	RestClient restClient;
	
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setUp() {
		baseClass = new BaseClass();
		serviceURL = prop.getProperty("URL");
		apiURL = prop.getProperty("ServiceURL");
		//"https://reqres.in/" + "/api/users?page=2"
		
		url = serviceURL + apiURL; 
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
		restClient = new RestClient();
		
		HashMap<String, String> newHashMap = new HashMap<String, String>();
		newHashMap.put("Content-Type", "application/json");	//Providing headers with url
		
		// convert UserData.java POJO file to JSON, do marshelling
		//jackSon API ... for marshelling and un-marshelling
		ObjectMapper mapper = new ObjectMapper();
		UserData userData = new UserData("morphous", "leader");	//expected user object
		
		//object to JSON
		mapper.writeValue(new File("C:\\eclipse-workspace\\RestAPIDemo\\src\\main\\java\\com\\qa\\data\\users.json"), userData );
		
		//object to JSON string
		String userDataJsonString = mapper.writeValueAsString(userData);
		
		closeableHttpResponse = restClient.post(url, userDataJsonString, newHashMap);
		
		//1. Status code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status Code --->"+ statusCode);
		Assert.assertEquals(statusCode, baseClass.RESPONSE_STATUS_CODE_201);
		
		//2. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response form API is --->"+ responseJson);
		
		//JSON to java Object un-marshelling
		UserData userDataResObj = mapper.readValue(responseString, UserData.class); //actual user object after calling API
		System.out.println(userDataResObj);
		
		Assert.assertTrue(userData.getName().equals(userDataResObj.getName()));
		Assert.assertTrue(userData.getJob().equals(userDataResObj.getJob()));
		
		System.out.println(userDataResObj.getId());
		System.out.println(userDataResObj.getCreatedAt());
	}
	
	
	
	
	

}
