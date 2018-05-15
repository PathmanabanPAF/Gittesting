package resttest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import GlobalClasses.Apipaths;

import com.fasterxml.jackson.annotation.JsonValue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class TC1_Get_location {

	
	public static void main(String[] args) throws JSONException
	{
		// TODO Auto-generated method stub

				//BaseURL name
				String address=ReadpropertyFile("TC1_Address");
				RestAssured.baseURI="https://maps.googleapis.com";
				
				Response res=given().
						//Parameters name
				       param("address",address).
				
				       when().
				       //Resource name
				       get(Apipaths.Get_location).
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
	

public static String ReadpropertyFile(String datasheetname)
{
	 File file = new File("F:/ResttestWS/ResttestWebservice/Config.properties");
	  System.out.println("Started");
	  
	  FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String filepath = null;
		//return prop.getProperty("URL");
		if(datasheetname.contentEquals("TC1_Address"))
		{
		 filepath=prop.getProperty("TC1_Address");
		}
		
		return filepath;		
		
}



}
