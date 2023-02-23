package com.REST_APIAssignment;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class BestBuyAPI {
	String productId;

	@BeforeMethod
	public void setUp() {
		baseURI = "http://localhost:3030";
	}

	@Test(priority = 0)
	public void getProduct() {

		Response response = given().param("$limit", 5).when().get("/products");
		response.then().assertThat().log().all().and().statusCode(200);
		productId = response.getBody().jsonPath().getString("data[0].id");
		System.out.println(productId);
	}

	@Test(priority = 1)
	public void getProductId() {
		System.out.println(productId);
		given().pathParam("id", productId)
		.when().get("/products/{id}").then().log().all()
		.and().assertThat().statusCode(200)
	    .and().assertThat().body("name", equalTo("Duracell - AA 1.5V CopperTop Batteries (4-Pack)"));

	}

	@Test(priority = 2)
	public void deleteProductId() {
		given().pathParam("id", productId)
		.when().delete("/products/{id}")
		.then().log().all()
		.and().assertThat().statusCode(200);
	}
}
