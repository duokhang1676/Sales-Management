package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import db.ConnectDB;
import entities.HangHoa;
import entities.TaiKhoan;

public class TaiKhoanDao {
	public TaiKhoanDao() {
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan timHangHoaTheoMa(String tenDangNhap) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from TaiKhoan where TenDangNhap = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tenDangNhap);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String mk = rs.getString("MatKhau");

				TaiKhoan tk = new TaiKhoan(tenDangNhap, mk);
				return tk;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean updateTaiKhoan(TaiKhoan tk) {
		ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection(); 
	    PreparedStatement stmt = null;
	    String sql = "UPDATE TaiKhoan SET "
	            + "MatKhau = ? "
	            + "WHERE TenDangNhap = ?";
	    try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, tk.getMatKhau());
	        stmt.setString(2, tk.getTenDangNhap());
	        
	        stmt.executeUpdate();
	        
	        return true;
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    return false;
	}
}
