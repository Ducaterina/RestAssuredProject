package restAPIXML;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;
import io.restassured.response.Response;

public class XMLTest {

	@Test
	public void test1() {
		
		RestAssured.given()
					.baseUri("https://chercher.tech/sample/api/books.xml")
					.when()
					.get()
					.then()
					.log()
					//.status()
					//.all()
					.body()
					.statusCode(200);
		
	}
	
	@Test
	public void test2() {
		
		Response response = RestAssured.given()
					.baseUri("https://chercher.tech/sample/api/books.xml")
					.when()
					.get();
		
		NodeChildrenImpl books = response.then().extract().path("bookstore.book.title");
	
		System.out.println("All the books are " + books.get(0).toString() + ", " + books.get(1).toString());
		System.out.println("First book is " + books.get(0).toString());
		System.out.println("Second book is " + books.get(1).toString());
		System.out.println("Language of the first book is " + books.get(1).getAttribute("lang").toString());
		
		System.out.println("The list of books via for loop: ");
        for(String i:books) {
			 System.out.println(i);
        }
        
        //for (int index=0; index<books.size();index++) {
        //System.out.println(books.get(index).toString());
		
		/*
		 * NodeChildrenImpl books1 =
		 * response.then().extract().path("bookstore.book.author");
		 * 
		 * System.out.println("The authors are " + books1.toString());
		 * System.out.println("First book's author is " + books1.get(0).toString());
		 * System.out.println("Second book's author is " + books1.get(1).toString());
		 */
		
        NodeChildrenImpl prices = response.then().extract().path("bookstore.book.price");
        System.out.println(prices.toString());
        System.out.println("The price of the first book is " +  prices.get(0).children().get("paperback"));
        System.out.println("The price of the second book is " +  prices.get(1).toString());
        
       
    	
		
	}
	
}
