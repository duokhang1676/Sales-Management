package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import components.Formater;
import db.ConnectDB;

import entities.HangHoa;
import entities.HoaDon;


public class HoaDonDao {
	public HoaDonDao() {
		// TODO Auto-generated constructor stub
	}
	public List<HoaDon> getHoaDonFromTo(LocalDate from, LocalDate to) {
		List<HoaDon> dsHoaDon = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * \r\n"
					+ "FROM HoaDon \r\n"
					+ "WHERE DATE(ThoiGianLapHoaDon) BETWEEN ? AND ?";
			
			PreparedStatement stmt=  null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, from.toString());
			stmt.setString(2, to.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String maHD = rs.getString("MaHoaDon");
                String tg = rs.getString("ThoiGianLapHoaDon");
                LocalDateTime thoiGianTao = LocalDateTime.parse(tg);
				double tienKhachTra = rs.getDouble("TienKhachTra");
				double tongTien = rs.getDouble("TongTien");
				String ghiChu = rs.getString("GhiChu");
				HoaDon hd = new HoaDon(maHD, thoiGianTao, tienKhachTra, ghiChu, tongTien);
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsHoaDon;
	} 
	public double getDoanhThuTheoThangNam(YearMonth yearMonth) {
		double tongTien = 0;
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT sum(tongtien) as doanhthu\r\n"
					+ "FROM HoaDon\r\n"
					+ "WHERE strftime('%Y-%m', ThoiGianLapHoaDon) = ?";
					
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, yearMonth.toString());
		
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			tongTien = rs.getDouble("doanhthu");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tongTien;
	}
	public double getTongTienHoaDonTrongKhoang(LocalDate from, LocalDate to) {
		double tongTien = 0;
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT SUM(TongTien) AS TongTienCacHoaDon\r\n"
					+ "FROM HoaDon\r\n"
					+ "WHERE DATE(ThoiGianLapHoaDon) BETWEEN ? AND ?";
					
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, from.toString());
			stmt.setString(2, to.toString());
		
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			tongTien = rs.getDouble("TongTienCacHoaDon");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tongTien;
	}

	
	public int getSoLuongHoaDonTrongKhoang(LocalDate from, LocalDate to) {
		int soLuong = 0;
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT COUNT(MaHoaDon) AS SoLuongHoaDon\r\n"
					+ "FROM HoaDon\r\n"
					+ "WHERE DATE(ThoiGianLapHoaDon) BETWEEN ? AND ?";
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, from.toString());
			stmt.setString(2, to.toString());
		
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			soLuong = rs.getInt("SoLuongHoaDon");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return soLuong;
	}
	public List<HoaDon> getAllHoaDon() {
		List<HoaDon> dsHoaDon = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from HoaDon";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString("MaHoaDon");
                String tg = rs.getString("ThoiGianLapHoaDon");
                LocalDateTime thoiGianTao = LocalDateTime.parse(tg);
				double tienKhachTra = rs.getDouble("TienKhachTra");
				double tongTien = rs.getDouble("TongTien");
				String ghiChu = rs.getString("GhiChu");
				HoaDon hd = new HoaDon(maHD, thoiGianTao, tienKhachTra, ghiChu, tongTien);
				dsHoaDon.add(hd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsHoaDon;
	}
	
	public boolean addHoaDon(HoaDon hd) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null;
		String sql = "insert into HoaDon (MaHoaDon,ThoiGianLapHoaDon, TienKhachTra,GhiChu, TongTien) "
				+ "values (?,?,?,?,?)";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hd.getMaHoaDon());
			stmt.setString(2, hd.getThoiGianLapHoaDon().toString());
			stmt.setDouble(3, hd.getTienKhachTra());
			stmt.setString(4, hd.getGhiChu());
			stmt.setDouble(5, hd.getTongTien());
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public HoaDon getHDbyMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from HoaDon where MaHoaDon = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString("MaHoaDon");
                String tg = rs.getString("ThoiGianLapHoaDon");
                LocalDateTime thoiGianTao = LocalDateTime.parse(tg);
				double tienKhachTra = rs.getDouble("TienKhachTra");
				double tongTien = rs.getDouble("TongTien");
				String ghiChu = rs.getString("GhiChu");
				HoaDon hd = new HoaDon(maHD, thoiGianTao, tienKhachTra, ghiChu, tongTien);
				return hd;
			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
}
