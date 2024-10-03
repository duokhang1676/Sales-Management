package components;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GeneratePK {
	public static String getMaHD() throws FileNotFoundException, IOException, ParseException {
		// Đọc file JSON
	    JSONParser parser = new JSONParser();
	    Object obj = parser.parse(new FileReader("config\\HoaDonID.json"));
	    
	    // Chuyển đổi object sang JSONObject
	    JSONObject jsonObject = (JSONObject) obj;
	    
	    // Lấy giá trị từ object
	    LocalDate date = LocalDate.parse((String) jsonObject.get("date"));
	    long countHD = (long) jsonObject.get("countHD");
	    
	    jsonObject = new JSONObject();
	    
	    jsonObject.put("date", LocalDate.now().toString());
	    
	    if(date.equals(LocalDate.now())) {
	    	jsonObject.put("countHD", ++countHD);
	    }else {
	    	
	    	jsonObject.put("countHD", 1);
	    	countHD = 1;
	    }
	    
        // Ghi object ra file JSON
        try (FileWriter file = new FileWriter("config\\HoaDonID.json")) {
            file.write(jsonObject.toJSONString());
        }
        
        String maHD = LocalDate.now().toString();
        
        String test[] = maHD.split("^(\\d\\d)|-");
        maHD = "HD";
        
        for (String string : test) {
			maHD+=string;
		}
        int leng = String.valueOf(countHD).length();
        if(leng==1)maHD+="000"+countHD;
        else if(leng==2)maHD+="00"+countHD;
        else if(leng==3)maHD+="0"+countHD;
        else maHD+=countHD;
        
        return maHD;
	}
	
	public static String getMaHDT() throws FileNotFoundException, IOException, ParseException {
		// Đọc file JSON
	    JSONParser parser = new JSONParser();
	    Object obj = parser.parse(new FileReader("config\\HoaDonTraID.json"));
	    
	    // Chuyển đổi object sang JSONObject
	    JSONObject jsonObject = (JSONObject) obj;
	    
	    // Lấy giá trị từ object
	    LocalDate date = LocalDate.parse((String) jsonObject.get("date"));
	    long countHD = (long) jsonObject.get("countHDT");
	    
	    jsonObject = new JSONObject();
	    
	    jsonObject.put("date", LocalDate.now().toString());
	    
	    if(date.equals(LocalDate.now())) {
	    	jsonObject.put("countHDT", ++countHD);
	    }else {
	    	
	    	jsonObject.put("countHDT", 1);
	    	countHD = 1;
	    }
	    
        // Ghi object ra file JSON
        try (FileWriter file = new FileWriter("config\\HoaDonTraID.json")) {
            file.write(jsonObject.toJSONString());
        }
        
        String maHD = LocalDate.now().toString();
        
        String test[] = maHD.split("^(\\d\\d)|-");
        maHD = "TH";
        
        for (String string : test) {
			maHD+=string;
		}
        int leng = String.valueOf(countHD).length();
        if(leng==1)maHD+="000"+countHD;
        else if(leng==2)maHD+="00"+countHD;
        else if(leng==3)maHD+="0"+countHD;
        else maHD+=countHD;
        
        return maHD;
	}

}
