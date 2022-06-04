package restAPI;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetRequestWithParams {
	@Test
	public void test1() {
		
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.param("id", "1").get();
		
		String ResponseBody = response.getBody().asString();
	
		System.out.println("Response body : " + ResponseBody);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
		System.out.println("Response code is " + ResponseCode);
		
		Assert.assertTrue(ResponseBody.contains("Pankaj"));
		
		JsonPath jpath = response.jsonPath();

		List<String> names = jpath.get("name");
		
		System.out.println(names.get(0));
		
		Assert.assertEquals(names.get(0), "Pankaj");
		
		String Header = response.getHeader("Content-Type");
		System.out.println("The header value is " + Header);
		
	}
}

