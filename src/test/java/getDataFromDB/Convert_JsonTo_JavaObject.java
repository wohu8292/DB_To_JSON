package getDataFromDB;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Convert_JsonTo_JavaObject {
	 public static void main(String[] args) throws StreamReadException, DatabindException, IOException{
		
		 
//		String jsonContent = FileUtils.readFileToString(new File("C:/Users/gjdnd/eclipse-workspace/DB_to_Json_OpenBook/custinfo.json"), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		File jsonFile = new File("C:\\Users\\gjdnd\\eclipse-workspace\\DB_to_Json_OpenBook\\custinfo0.json");
		CustomerDetails cd = mapper.readValue(jsonFile, CustomerDetails.class);
		System.out.println(cd.getName());
		System.out.println(cd.getCity());
		System.out.println(cd.getCountry());

	}
}
