package restAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequest {
	@Test
	public void test1() {
		
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.get();
		
		String ResponseBody = response.getBody().asString();
	
		System.out.println("Response body : " + ResponseBody);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
		
		System.out.println("Response code is " + ResponseCode);
		
		JsonPath jpath = response.jsonPath();

		List<String> names = jpath.get("name");
//		for (String s:names) {
//			System.out.println(s);
//		}
		System.out.println(names);
		
	}
}
