package restBDD;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequestBDD {

	@Test
	public void test1() {
		
		RestAssured.given()
					.baseUri("http://localhost:3000/employees")
					.when()
					.get()
					.then()
					.log()
					//.status()
					.all()
					//.body()
					.statusCode(200);
		
	}
	
	@Test
	public void test2() {
		
		RestAssured.given()
					.baseUri("http://localhost:3000/employees")
					.queryParam("id", "2")
					.when()
					.get()
					.then()
					.log()
					//.status()
					//.all()
					.body()
					.statusCode(200)
					.body("[0].name", Matchers.equalTo("David"));
		//because we're getting only 1 item in the response, it should ALWAYS be [0]
	}
	
	@Test
	public void test3() {
		Response response = RestAssured.given()
										.baseUri("http://localhost:3000/employees")
										.queryParam("id", "2")
										.when()
										.get();
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
