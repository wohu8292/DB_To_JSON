package getDataFromDB;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
// convert multiple object to multiple json file
// table - arrayList - json
public class ConvertDBResult_JavaObject_JsonFile {
	public static void main(String[] args) throws SQLException, StreamWriteException, DatabindException, IOException {
		//1.Create Connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "AFslg175!@$");
		
		//2.Create Query (Statement)
		Statement statement = con.createStatement();
		String s = "SELECT * FROM customers WHERE country='USA' Limit 3;";
		
		//3.Execute Query and Creating arrayList to store javaObject
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
			System.out.println(cd);
			customerList.add(cd);
			}
		//5.Using Jackson (core, databind, annotations), convert java object into JSON file.
		for(int i=0; i<customerList.size(); i++) {
		File jsonfile = new File("C:/Users/gjdnd/eclipse-workspace/DB_to_Json_OpenBook/custinfo"+i+".json");
		ObjectMapper mapper = new ObjectMapper();

		// array[i], arrayList.get(i)
		mapper.writeValue(jsonfile, customerList.get(i));
		}
		
		con.close();
		System.out.println("done");
		}
}
