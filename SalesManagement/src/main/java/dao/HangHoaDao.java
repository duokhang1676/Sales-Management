package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.ConnectDB;
import entities.HangHoa;

public class HangHoaDao {
	public List<HangHoa> getAllDataHangHoa() {
		List<HangHoa> dsHangHoa = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * FROM HangHoa";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String maHH = rs.getString("MaHangHoa");
				String tenHH = rs.getString("TenHangHoa");
				


				String nhomHang = rs.getString("NhomHang");
				String donViTinh = rs.getString("DonViTinh");
				String moTa = rs.getString("MoTa");
				String maVach = rs.getString("MaVach");
				double giaBan = rs.getDouble("GiaBan");
				int soLuongDinhMuc = rs.getInt("SoLuongDinhMuc");
				int soLuongCanhBao = rs.getInt("SoLuongCanhBao");
				
				HangHoa hangHoa = new HangHoa(maHH, tenHH, nhomHang,donViTinh, moTa,maVach,giaBan, soLuongDinhMuc, soLuongCanhBao);
				
				dsHangHoa.add(hangHoa);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHangHoa;
	}
	public HangHoa timHangHoaTheoMa(String maHHoa) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from HangHoa where MaHangHoa = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHHoa);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHH = rs.getString("MaHangHoa");
				String tenHH = rs.getString("TenHangHoa");
				


				String nhomHang = rs.getString("NhomHang");
				String donViTinh = rs.getString("DonViTinh");
				String moTa = rs.getString("MoTa");
				String maVach = rs.getString("MaVach");
				double giaBan = rs.getDouble("GiaBan");
				int soLuongDinhMuc = rs.getInt("SoLuongDinhMuc");
				int soLuongCanhBao = rs.getInt("SoLuongCanhBao");
				
				HangHoa hangHoa = new HangHoa(maHH, tenHH, nhomHang,donViTinh, moTa,maVach,giaBan, soLuongDinhMuc, soLuongCanhBao);
				
				return hangHoa;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
//Đang làm	
	public HangHoa timHangHoaTheoMaVachVaMaHH(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from HangHoa \r\n"
					+ "where MaVach = ? or MaHangHoa = ?\r\n";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			stmt.setString(2, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHH = rs.getString("MaHangHoa");
				HangHoa hangHoa = timHangHoaTheoMa(maHH);
				return hangHoa;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
	public List<HangHoa> getHangHoaHetHang() {
		List<HangHoa> dsHangHoa = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HangHoa\r\n"
					+ "where SoLuongDinhMuc <= SoLuongCanhBao";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maHH = rs.getString("MaHangHoa");
				String tenHH = rs.getString("TenHangHoa");
				String nhomHang = rs.getString("NhomHang");
				String donViTinh = rs.getString("DonViTinh");
				String moTa = rs.getString("MoTa");
				String maVach = rs.getString("MaVach");
				double giaBan = rs.getDouble("GiaBan");
				int soLuongDinhMuc = rs.getInt("SoLuongDinhMuc");
				int soLuongCanhBao = rs.getInt("SoLuongCanhBao");
				
				HangHoa hangHoa = new HangHoa(maHH, tenHH, nhomHang,donViTinh, moTa,maVach,giaBan, soLuongDinhMuc, soLuongCanhBao);
				
				dsHangHoa.add(hangHoa);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHangHoa;
	}
			
	
	
	
	public boolean themHangHoa(HangHoa hh) {
	    int affectedRows = 0;
	    ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection(); 
	    PreparedStatement stmt = null;
	    String sql = "INSERT INTO HangHoa (\r\n"
	    		+ "    TenHangHoa, \r\n"
	    		+ "    NhomHang, \r\n"
	    		+ "    DonViTinh, \r\n"
	    		+ "    MoTa, \r\n"
	    		+ "    MaVach, \r\n"
	    		+ "    GiaBan, \r\n"
	    		+ "    SoLuongDinhMuc, \r\n"
	    		+ "    SoLuongCanhBao\r\n"
	    		+ ") \r\n"
	    		+ "VALUES (\r\n"
	    		+ "    ?, \r\n"
	    		+ "    ?, \r\n"
	    		+ "    ?, \r\n"
	    		+ "    ?, \r\n"
	    		+ "    ?, \r\n"
	    		+ "    ?, \r\n"
	    		+ "    ?, \r\n"
	    		+ "    ?\r\n"
	    		+ ");\r\n";

	    try {
	        stmt = con.prepareStatement(sql);

	        stmt.setString(1, hh.getTenHangHoa());
	        stmt.setString(2, hh.getNhomHang());
	        stmt.setString(3, hh.getDonViTinh());
	        stmt.setString(4, hh.getMoTa());
	        stmt.setString(5, hh.getMaVach());
	        stmt.setDouble(6, hh.getGiaBan());
	        stmt.setInt(7, hh.getSoLuongDinhMuc());
	        stmt.setInt(8, hh.getSoLuongCanhBao());

	        stmt.executeUpdate();
			stmt.close();
			return true;
	        
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	public boolean updateHangHoa(HangHoa hh, String maHH) {
		ConnectDB.getInstance();
	    Connection con = ConnectDB.getConnection(); 
	    PreparedStatement stmt = null;
	    String sql = "UPDATE HangHoa SET "
	            + "TenHangHoa = ?, "
	            + "NhomHang = ?, "
	            + "DonViTinh = ?, "
	            + "MoTa = ?, "
	            + "MaVach = ?, "
	            + "GiaBan = ?, "
	            + "SoLuongDinhMuc = ?, "
	            + "SoLuongCanhBao = ?, "
	            + "WHERE MaHangHoa = ?";
	    try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hh.getTenHangHoa());
	        stmt.setString(2, hh.getNhomHang());
	        stmt.setString(3, hh.getDonViTinh());
	        stmt.setString(4, hh.getMoTa());
	        stmt.setString(5, hh.getMaVach());
	        stmt.setDouble(6, hh.getGiaBan());
	        stmt.setInt(7, hh.getSoLuongDinhMuc());
	        stmt.setInt(8, hh.getSoLuongCanhBao());
	        
	        stmt.setString(9, maHH);
	        
	        stmt.executeUpdate();
	        
	        return true;
	        
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	    return false;
	}
	
	public boolean capNhatSoLuongHangHoa(HangHoa hangHoa) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null;			
		try {
			String sql = "update HangHoa set SoLuongDinhMuc = ? where HangHoa.MaHangHoa = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, hangHoa.getSoLuongDinhMuc());
			stmt.setString(2, hangHoa.getMaHangHoa());
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	
	public List<HangHoa> locHangHoaTheoNhomHang(int nh) {
		List<HangHoa> dsHangHoa = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from HangHoa left join NhomHang on HangHoa.MaNhomHang = NhomHang.MaNhomHang "
					+ "where HangHoa.MaNhomHang = ?";
					
			stmt = con.prepareStatement(sql);
			
			
			stmt.setInt(1, nh);
			
			
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHH = rs.getString("MaHangHoa");
				String tenHH = rs.getString("TenHangHoa");
				


				String nhomHang = rs.getString("NhomHang");
				String donViTinh = rs.getString("DonViTinh");
				String moTa = rs.getString("MoTa");
				String maVach = rs.getString("MaVach");
				double giaBan = rs.getDouble("GiaBan");
				int soLuongDinhMuc = rs.getInt("SoLuongDinhMuc");
				int soLuongCanhBao = rs.getInt("SoLuongCanhBao");
				
				HangHoa hangHoa = new HangHoa(maHH, tenHH, nhomHang,donViTinh, moTa,maVach,giaBan, soLuongDinhMuc, soLuongCanhBao);
				
				dsHangHoa.add(hangHoa);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHangHoa;
	}
}
