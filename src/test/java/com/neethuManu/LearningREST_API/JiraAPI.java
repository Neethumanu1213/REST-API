package com.neethuManu.LearningREST_API;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class JiraAPI {
	
	String authorization="Basic bmVldGh1bWFudTEyMTNAZ21haWwuY29tOkFUQVRUM3hGZkdGMDlMSUJTeWdyRVpUUzQyVkNuS3libUdYdEQ5dURkSUctRHFPRFBxQ1Y0RGh5NEgzbWlsRHhacURQWnprdFN2a1FVZmZrRk5BSzA0dXdxY1ZTTGpoZ0g0bGtHNHN0aXBQdzdVa2ZqMGdsc2Z3WVRsV1lnOW9tQWxmb01PdkJNXzlKdWNRb2tYclhGem9vYnZLOWpBbUZVNTJBWk4wY05CTlI1NFhLSkxEMmxQND0xOTBGNTdCQw==";
	String storyID="";
	
	@BeforeMethod
	public void setup() {
		baseURI="https://neethumanu1213.atlassian.net";
	}

	@Test
	public void getEvents() {
		
		given().header("Authorization", authorization) 
		.when().get("/rest/api/3/events")
		.then().log().all();
	}
	
	@Test(priority=0)
	public void getIssueMetadata() {
		
//		 given().header("Authentication", authorization)
//		 .when().get("/rest/api/3/issue/createmeta")
//		 .then().log().all(); 
		 
		 Response response= given().header("Authorization", authorization)
				 .when().get("/rest/api/3/issue/createmeta");
	    storyID=response.getBody().jsonPath().getString("projects[0].issuetypes[0].id");
		System.out.println(storyID);
	}
	
	@Test(priority = 1)
	public void createIssue() {
		
		String requestBodyForCeateIssue="{\r\n"
				+ "  \"fields\": {\r\n"
				+ "    \"issuetype\": {\r\n"
				+ "      \"id\":\""+storyID+ "\"\r\n"
				+ "    },\r\n"
				+ "    \r\n"
				+ "    \"project\": {\r\n"
				+ "      \"id\": \"10001\"\r\n"
				+ "    },\r\n"
				+ "    \"reporter\": {\r\n"
				+ "      \"id\": \"6325e3564395a525a712b2a0\"\r\n"
				+ "    },\r\n"
				+ "    \r\n"
				+ "    \"summary\": \"Creating issue using postman\"\r\n"
				+ "   }\r\n"
				+ "  }";
		
		 given().header("Authorization", authorization).header("Content-Type","application/json").body(requestBodyForCeateIssue)
		 .when().log().all().post("/rest/api/3/issue")
		 .then().log().all(); 
	}
	
	
}
