package components;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPasswordSHA256 {
	 public static String hashPassword(String password) {
	        try {
	            // Tạo instance của MessageDigest với thuật toán SHA-256
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            
	            // Chuyển đổi mật khẩu thành mảng byte và băm nó
	            byte[] hashedBytes = md.digest(password.getBytes());
	            
	            // Chuyển đổi mảng byte thành chuỗi hex
	            StringBuilder sb = new StringBuilder();
	            for (byte b : hashedBytes) {
	                sb.append(String.format("%02x", b));
	            }
	            
	            // Trả về mật khẩu đã băm dưới dạng chuỗi hex
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException("Không tìm thấy thuật toán SHA-256", e);
	        }
	    }
}
