package resttest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.sql.ResultSetMetaData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Post_AddPlace {


	public static void main(String[] args) throws JSONException
	{
				
		System.out.println("POSt done");
		
		//BASE URL 
		RestAssured.baseURI ="https://maps.googleapis.com";
		RequestSpecification request = RestAssured.given();
	 
		//Add parameter
		request.queryParam("key", "AIzaSyBRfeKi6O-M6lM8Dz3EsmBbvPXCYkTb1sA"); // Cast
	
		//Add Body XML
		request.body("{"+
				"\"location\": {"+
				"\"lat\": -33.8669710,"+
				"\"lng\": 151.1958750"+
				"},"+
				"\"accuracy\": 50,"+
				"\"name\": \"Google Shoes!\","+
				"\"phone_number\": \"(02) 9374 4000\","+
				"\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
				"\"types\": [\"shoe_store\"],"+
				"\"website\": \"http://www.google.com.au/\","+
				"\"language\": \"en-AU\""+
				"}");

		
		//Add Resource & POST
		Response response = request.when().post("/maps/api/place/add/json");
		
		   
		//Validate response
		String res=response.asString();
		System.out.println("response"+res);
		res="["+res+"]";
		
		//Validating Response
		JSONArray jsonArrayResponseBody;
		
		jsonArrayResponseBody=new JSONArray(res);
		
		JSONObject message=jsonArrayResponseBody.getJSONObject(0);
		System.out.println("JSON Object: "+message);
	    
		int statusCode = response.getStatusCode();
		System.out.println("Response"+response);
		System.out.println("status: "+statusCode);
		//Assert.assertEquals(statusCode, "201");
		
	}
}
