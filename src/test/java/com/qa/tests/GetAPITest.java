package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.http.client.methods.CloseableHttpResponse;

import com.qa.client.RestClient;
import com.qa.util.TestUtil;
import com.qa.base.BaseClass;;

public class GetAPITest extends BaseClass {

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
	
	@Test(priority=1)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		
		//a. Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode(); //Status Code
		System.out.println("Status Code --->"+ statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//b. Response String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8"); //response String
		JSONObject responseJson = new JSONObject(responseString); //response string will convert into JSON object i.e key value pairs
		System.out.println("Responser JSON from API --->"+ responseJson);
		
		//get Single Value from JSON and Assertion
		//per_page
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of Per Page --->"+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		//total
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of Total  --->"+ totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//get value from JSON Array and Assertion
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println("Value of Last Name --->"+ lastName);
		System.out.println("Value of ID --->"+ id);
		System.out.println("Value of Avatar --->"+ avatar);
		System.out.println("Value of First Name --->"+ firstName);

		
		Assert.assertEquals(lastName, "Holt");
		Assert.assertEquals(Integer.parseInt(id), 4);
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg");
		Assert.assertEquals(firstName, "Eve");
		
		//c. Headers
		Header [] headerArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeader = new HashMap<String, String>();
		
		for(Header header : headerArray) {
			allHeader.put(header.getName(), header.getValue());
		}
		
		System.out.println("Header Array --->"+ allHeader);
		
		
		
	}
	
	
	
	
	@Test(priority=2)
	public void getAPITestWithHeader() throws ClientProtocolException, IOException{
		restClient = new RestClient();
		
		HashMap<String, String> newHashMap = new HashMap<String, String>();
		newHashMap.put("Content-Type", "application/json");	//Providing headers with url
//		newHashMap.put("User Name", "test@Amazon.com");
//		newHashMap.put("Password", "123Abc@#$");
//		newHashMap.put("Auth Token", "123654");
		
		closeableHttpResponse = restClient.get(url);
		
		//a. Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode(); //Status Code
		System.out.println("Status Code --->"+ statusCode);
		
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");
		
		//b. Response String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8"); //response String
		JSONObject responseJson = new JSONObject(responseString); //response string will convert into JSON object i.e key value pairs
		System.out.println("Responser JSON from API --->"+ responseJson);
		
		//get Single Value from JSON and Assertion
		//per_page
		String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
		System.out.println("Value of Per Page --->"+ perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 3);
		
		//total
		String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
		System.out.println("Value of Total  --->"+ totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);
		
		//get value from JSON Array and Assertion
		String lastName = TestUtil.getValueByJPath(responseJson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responseJson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responseJson, "/data[0]/avatar");
		String firstName = TestUtil.getValueByJPath(responseJson, "/data[0]/first_name");
		
		System.out.println("Value of Last Name --->"+ lastName);
		System.out.println("Value of ID --->"+ id);
		System.out.println("Value of Avatar --->"+ avatar);
		System.out.println("Value of First Name --->"+ firstName);

		
		Assert.assertEquals(lastName, "Holt");
		Assert.assertEquals(Integer.parseInt(id), 4);
		Assert.assertEquals(avatar, "https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg");
		Assert.assertEquals(firstName, "Eve");
		
		//c. Headers
		Header [] headerArray = closeableHttpResponse.getAllHeaders();
		
		HashMap<String, String> allHeader = new HashMap<String, String>();
		
		for(Header header : headerArray) {
			allHeader.put(header.getName(), header.getValue());
		}
		
		System.out.println("Header Array --->"+ allHeader);
		
	}
}
