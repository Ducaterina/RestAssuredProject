package apiChaining;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class EndToEndTest {

	String BaseURI = "http://localhost:3000/employees";
	
	@Test
	public void test1() {
		
		Response response;
		
		response = GetMethodAll();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		response = PostMethod("Luchia", "2000");
		Assert.assertEquals(response.getStatusCode(), 201);
		JsonPath Jpath =response.jsonPath();
        int EmpID = Jpath.get("id");
        System.out.println("id"+EmpID);
        
        response = PutMethod(EmpID, "Raj", "6000");
        Assert.assertEquals(response.getStatusCode(), 200);
        Jpath =response.jsonPath();
        String Name = Jpath.get("name");
        Assert.assertEquals(Name, "Raj");
        System.out.println("Updated Name is "+ Name);
        
        response = DeleteMethod(EmpID);
        String ResponseBody = response.getBody().asString();
		Assert.assertEquals(ResponseBody, "{}");	
		int ResponseCode = response.getStatusCode();
		Assert.assertEquals(ResponseCode, 200);
		
		 response = GetMethod(EmpID);
	     Assert.assertEquals(response.getBody().asString(), "{}");	
		 Assert.assertEquals(response.getStatusCode(), 404);
		
	}
	
	public Response GetMethodAll() {
		
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get();
		
		return response;
		
	}
	
	public Response PostMethod(String Name, String Salary) {
		RestAssured.baseURI = BaseURI;
		
	
		JSONObject jobj = new JSONObject();
		
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(jobj.toString())
					.post("/create");
		
		return response;
	}
	
	public Response PutMethod(int EmpID, String Name, String Salary) {
		RestAssured.baseURI = BaseURI;
		
	
		JSONObject jobj = new JSONObject();
		
		jobj.put("name", Name);
		jobj.put("salary", Salary);
		
		RequestSpecification request = RestAssured.given();
		
		Response response = request.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(jobj.toString())
					.put("/" + EmpID);
		
		return response;
	}
	
	public Response DeleteMethod(int EmpID) {
		
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.delete("/" + EmpID);
		
		return response;
	}
	
	public Response GetMethod(int EmpID) {
		
		RestAssured.baseURI = BaseURI;
		RequestSpecification request = RestAssured.given();
		Response response = request.get("/" + EmpID);
		
		return response;
	}
	
}
		
	
	