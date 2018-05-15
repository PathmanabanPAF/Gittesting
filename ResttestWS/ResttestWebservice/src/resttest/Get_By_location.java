package resttest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonValue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Get_By_location {

	
	public static void main(String[] args) throws JSONException
	{
		// TODO Auto-generated method stub

				//BaseURL name
				RestAssured.baseURI="https://maps.googleapis.com";
				
				Response res=given().
						//Parameters name
				       param("address","Hyderabad").
				
				       when().
				       //Resource name
				       get("/maps/api/geocode/json").
				       then().extract().response();
				       
				
				String response=res.asString();
				System.out.println("response"+response);
				response="["+response+"]";
				       /*header("dfd","fsdfds").
				       cookie("dsfs","csder").
				       body()*/
				//Status code of the response
				//Content type 
				//Body
				//Header responses
				
				//Validating Response
				JSONArray jsonArrayResponseBody;
				jsonArrayResponseBody=new JSONArray(response);
				
				JSONObject message=jsonArrayResponseBody.getJSONObject(0);
				System.out.println("JSON Object: "+message);
				JSONArray places = (JSONArray)message.get("results");
				
				//String placeid=message.getString("Place_id");
				System.out.println("PLACE : "+places);
				
				for (int i = 0; i < places.length(); i++) {
				    JSONObject jsonobject = places.getJSONObject(i);
				    String name = jsonobject.getString("place_id");
				    System.out.println("PLACE:     "+name);
				    
				    //String url = jsonobject.getString("url");
				}
				
				
	}
}
