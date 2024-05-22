package getDataFromDB;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.google.gson.Gson;
// convert multiple object to multiple json file
// table - arrayList - json
public class ConvertDBResult_JavaObject_SingleJsonFile {
	public static void main(String[] args) throws SQLException, StreamWriteException, DatabindException, IOException {
		//1.Create Connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "AFslg175!@$");
		
		//2.Create Query (Statement)
		Statement statement = con.createStatement();
		String s = "SELECT * FROM customers WHERE country='USA' Limit 5;";
		
		//3.Execute Query and Store the Result
		ResultSet result = statement.executeQuery(s);
		ArrayList<CustomerDetails> customerList = new ArrayList<CustomerDetails>();
		
		//4.Print Result
		while(result.next()) {
			int number = result.getInt("customerNumber");
			String name = result.getString("customerName");
			String city = result.getString("city");
			String state = result.getString("state");
			String country = result.getString("country");
			
			//recreate object
			CustomerDetails cd = new CustomerDetails();

			cd.setName(name);
			cd.setCity(city);
			cd.setState(state);
			cd.setCountry(country);
			
			customerList.add(cd);
			}
		//5. Java Object - JSON String - JSON Array - JSON Object (Dependencies: GSON, json.simple)
		
		JSONArray jsonArray = new JSONArray();
		
		for(int i=0; i<customerList.size(); i++) {
			// Converting Java object into JSON string
			Gson g = new Gson();
			String jsonString = g.toJson(customerList.get(i));
			jsonArray.add(jsonString);
			}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("data", jsonArray);
		String jsonFormattedString = jsonObject.toJSONString().replace("\\\"", "\"");
		String finalJSON = jsonFormattedString.replace("\"{","{").replace("}\"","}");
		System.out.println(finalJSON);
		con.close();
		System.out.println("done");
		}
}
