package com.neethuManu.LearningREST_API;


import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class SimpleBookAPI {
	
	@BeforeMethod
	public void setup() {
		
		baseURI="https://simple-books-api.glitch.me";
	}
	
	@Test
	public void getListOfBook() {
		given().param("type", "fiction")
		.when().get("/books")
		.then().log().all().assertThat().statusCode(200);
	}
	
	@Test
	public void getASingleBook() {
		given().pathParam("bookId", 3)
		.when().get("/books/{bookId}")
		.then().log().all()
		.assertThat().statusCode(200)
		.and().body("name",equalTo("The Vanishing Half"))
		.and().body("id", notNullValue());
	}

}
