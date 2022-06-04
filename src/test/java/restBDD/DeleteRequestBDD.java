package restBDD;


import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;


public class DeleteRequestBDD {

	@Test
	public void test1() {
		
	RestAssured.given()
				.baseUri("http://localhost:3000/employees")
				.when()
				.delete("/18")
				.then()
				.log()
				//.status()
				//.all()
				.body()
				.statusCode(200)
				.body("name", Matchers.equalTo(null));
	
}
}
