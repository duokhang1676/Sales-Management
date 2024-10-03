package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import db.ConnectDB;

import entities.HoaDon;

import entities.PhieuXuatTra;


public class PhieuXuatTraDao {
	public double getTienTraTheoThangNam(YearMonth ym) {
		double tongTien = 0;
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT sum(thanhtien) as chiphi\r\n"
					+ "FROM phieuxuattra\r\n"
					+ "WHERE strftime('%Y-%m', ThoiGianTao) = ?";
					
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ym.toString());
			
		
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			tongTien = rs.getDouble("tientra");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tongTien;
	}
	public List<PhieuXuatTra> getAllDataPXTbyDate(LocalDate from, LocalDate to) {
		List<PhieuXuatTra> dspxt = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		to = to.plusDays(1);
		try {
			String sql = "select * from PhieuXuatTra where ThoiGianTao >= ? and ThoiGianTao <= ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			stmt.setString(1, LocalDate.parse(from.toString(),format).toString());
			stmt.setString(2, LocalDate.parse(to.toString(),format).toString());
			ResultSet rs = stmt.executeQuery();
			String defaultTime = "00:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			while (rs.next()) {
				String mapxt = rs.getString("MaPhieuXuatTra");
				String date = rs.getString("ThoiGianTao");
				LocalDateTime thoiGianTao = LocalDateTime.parse(date, formatter);
//				LocalDate thoiGianTao = LocalDate.parse(rs.getString("ThoiGianTao"));
				
				String ghiChu = rs.getString("GhiChu");
		
				double tienGiam = rs.getDouble("TongGiamGia");
				double tongTienHang = rs.getDouble("TongTienHang");
	
				
				PhieuXuatTra pxt = new PhieuXuatTra(mapxt, thoiGianTao, ghiChu, tienGiam, tongTienHang);
				dspxt.add(pxt);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dspxt;
	}
	public List<PhieuXuatTra> getAllDataPXT() {
		List<PhieuXuatTra> dspxt = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuXuatTra";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String defaultTime = "00:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			while (rs.next()) {
				String mapxt = rs.getString("MaPhieuXuatTra");
				String date = rs.getString("ThoiGianTao");
				LocalDateTime thoiGianTao = LocalDateTime.parse(date, formatter);
//				LocalDate thoiGianTao = LocalDate.parse(rs.getString("ThoiGianTao"));
				
				String ghiChu = rs.getString("GhiChu");
				double tienGiam = rs.getDouble("TongGiamGia");
				double tongTienHang = rs.getDouble("TongTienHang");
	
				
				PhieuXuatTra pxt = new PhieuXuatTra(mapxt, thoiGianTao, ghiChu, tienGiam, tongTienHang);
				dspxt.add(pxt);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dspxt;
	}
	
	public boolean createPXT(PhieuXuatTra pxt) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null;
		String sql = "insert into PhieuXuatTra (MaPhieuXuatTra, ThoiGianTao, GhiChu, TongGiamGia, TongTienHang, ThanhTien) "
				+ "values (?,?,?,?,?,?)";
		
		try {
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, pxt.getMaPhieuXuatTra());
			stmt.setString(2, pxt.getThoiGianTao().toString());
			stmt.setString(3, pxt.getGhiChu());
			stmt.setDouble(4, pxt.getTongGiamGia());
			stmt.setDouble(5, pxt.getTongTienHang());
			stmt.setDouble(6, pxt.tinhThanhTien());
			
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public PhieuXuatTra getPXTTheoMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from PhieuXuatTra where MaPhieuXuatTra = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			while (rs.next()) {
				String mapxt = rs.getString("MaPhieuXuatTra");
				String date = rs.getString("ThoiGianTao");
				LocalDateTime thoiGianTao = LocalDateTime.parse(date, formatter);
//				LocalDate thoiGianTao = LocalDate.parse(rs.getString("ThoiGianTao"));
			
				String ghiChu = rs.getString("GhiChu");

				double tienGiam = rs.getDouble("TongGiamGia");
				double tongTienHang = rs.getDouble("TongTienHang");
				
				PhieuXuatTra pxt = new PhieuXuatTra(mapxt, thoiGianTao, ghiChu, tienGiam, tongTienHang);
				return pxt;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
