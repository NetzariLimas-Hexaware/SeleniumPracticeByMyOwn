package Hexaware.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Hexaware.TestComponents.BaseTest;

public class DataReader {
	public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\Hexaware\\data\\Purchase.json"),
				StandardCharsets.UTF_8);
		
		// Jackson Databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		
		return data;
	}
}