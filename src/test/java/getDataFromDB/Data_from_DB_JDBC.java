package getDataFromDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//1. Create Connection
//2. Create Query (Statement)
//3. Execute Query and Store the Result
//4. Print Result

public class Data_from_DB_JDBC {
	public static void main(String[] args) throws SQLException {
	//1.Create Connection
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "AFslg175!@$");
	//2.Create Query (Statement)
	Statement statement = con.createStatement();
	String s = "SELECT * FROM customers WHERE country='USA' limit 5";
	//3.Execute Query and Store the Result
	ResultSet result = statement.executeQuery(s);
	//4.Print Result
	while(result.next()) {
		int number = result.getInt("customerNumber");
		String name = result.getString("customerName");
		String city = result.getString("city");
		String state = result.getString("state");
		String country = result.getString("country");
		
		System.out.println(name + "  " + number + "  " + city + "  " + state + "  " + country);
		}
	con.close();
	}
}
