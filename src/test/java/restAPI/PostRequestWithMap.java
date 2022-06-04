package restAPI;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRequestWithMap {

	@Test
	public void test1() {
		RestAssured.baseURI = "http://localhost:3000/employees";
		
		Map<String, Object> MapObj = new HashMap<String, Object>();
		
		MapObj.put("name", "Kate");
		MapObj.put("salary", "3000");
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(MapObj)
					.post("/create");
		
		String ResponseBody = response.getBody().asString();
		
		System.out.println("Response body : " + ResponseBody);
		
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 201);
		
		System.out.println("Response code is " + ResponseCode);
		
		JsonPath Jpath =response.jsonPath();
        Jpath.get("id");
        System.out.println("id"+Jpath.get("id"));
	}
}
