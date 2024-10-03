package components;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Setting {
	public static boolean getNhoTK() {
		// Đọc file JSON
	    JSONParser parser = new JSONParser();
	    Object obj = null;
		try {
			obj = parser.parse(new FileReader("config\\Setting.json"));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    // Chuyển đổi object sang JSONObject
	    JSONObject jsonObject = (JSONObject) obj;
	    
	    // Lấy giá trị từ object
	    boolean remember = (boolean) jsonObject.get("rememberTK");
	    String latestUser = jsonObject.get("latestUser").toString();
	    String latestPass = jsonObject.get("latestPass").toString();
	    LoginInfo.latestTK.setTenDangNhap(latestUser);
	    LoginInfo.latestTK.setMatKhau(latestPass);
	    
	    return remember;   
	}
	public static void setNhoTK(boolean remember) {
	    // Chuyển đổi object sang JSONObject
	    JSONObject jsonObject = new JSONObject();
		 // Ghi object ra file JSON
        try (FileWriter file = new FileWriter("config\\Setting.json")) {
        	jsonObject.put("rememberTK", remember);
        	jsonObject.put("latestUser", LoginInfo.latestTK.getTenDangNhap());
        	jsonObject.put("latestPass", LoginInfo.latestTK.getMatKhau());
        	
            file.write(jsonObject.toJSONString());
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
