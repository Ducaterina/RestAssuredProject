package gitHub;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {

	
	@Test
	public void test1() throws InterruptedException {
		

		RestAssured.baseURI = "https://api.github.com/users/Ducaterina/repos";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.accept(ContentType.JSON).get();
		Thread.sleep(5000);
		
		String ResponseBody = response.getBody().asString();
	
		System.out.println("Response body : " + ResponseBody);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
		
		System.out.println("Response code is " + ResponseCode);
	
	}
		@Test
		public void test2() throws IOException {
			
			RestAssured.baseURI = "https://api.github.com/user/repos";
			
			byte[] dataBytes = Files.readAllBytes(Paths.get("data.json"));
		
			RequestSpecification request = RestAssured.given();
			
			Response response = request.contentType(ContentType.JSON)
										.accept(ContentType.JSON)
										.auth()
										.oauth2("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")
										.body(dataBytes)
										.post();
			
			String ResponseBody = response.getBody().asString();
			
			System.out.println("Response body : " + ResponseBody);
			
			int ResponseCode = response.getStatusCode();
			Assert.assertEquals(ResponseCode, 201);
			
			System.out.println("Response code is " + ResponseCode);
		}
		
		@Test
		public void test3() {
			RestAssured.baseURI = "https://api.github.com/repos/Ducaterina/PostmanDemo";
			
			RequestSpecification request = RestAssured.given();
			
			Response response = request.contentType(ContentType.JSON)
										.accept(ContentType.JSON)
										.auth()
										.oauth2("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")
										.body("{\r\n"
												+ "    \"name\": \"RestAssuredTestFolder\"\r\n"
												+ "}")
										.patch();
			
			int ResponseCode = response.getStatusCode();
			Assert.assertEquals(ResponseCode, 200);
			System.out.println("Response code is " + ResponseCode);
			
			JsonPath Jpath =response.jsonPath();
	        Jpath.get("name");
	        System.out.println("The new name is "+Jpath.get("name"));
		}
		
		@Test
		public void test4() {
			
			RestAssured.baseURI = "https://api.github.com/repos/Ducaterina";
			
			RequestSpecification request = RestAssured.given();
					
			Response response = request.contentType(ContentType.JSON)
										.accept(ContentType.JSON)
										.auth()
										.oauth2("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")
										.delete("/RestAssuredTestFolder");
			
			String ResponseBody = response.getBody().asString();
		
			System.out.println("Response body : " + ResponseBody);
			
			int ResponseCode = response.getStatusCode();
			Assert.assertEquals(ResponseCode, 204);
			
			System.out.println("Response code is " + ResponseCode);
		}
		}

