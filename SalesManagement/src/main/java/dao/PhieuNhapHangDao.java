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

import entities.PhieuNhapHang;


public class PhieuNhapHangDao {
	public double getChiPhiTheoThangNam(YearMonth ym) {
		double tongTien = 0;
		try {
			db.ConnectDB.getInstance();
			Connection con = db.ConnectDB.getConnection();
			String sql = "SELECT sum(thanhtien) as chiphi\r\n"
					+ "FROM phieunhaphang\r\n"
					+ "WHERE strftime('%Y-%m', ThoiGianTao) = ?";
					
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ym.toString());
			
		
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			tongTien = rs.getDouble("chiphi");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tongTien;
	}
	public List<PhieuNhapHang> getAllDataPNHbyDate(LocalDate from, LocalDate to) {
		List<PhieuNhapHang> dsPNH = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		to = to.plusDays(1);
		try {
			String sql = "select * from PhieuNhapHang where ThoiGianTao >= ? and ThoiGianTao <= ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			stmt.setString(1, LocalDate.parse(from.toString(),format).toString());
			stmt.setString(2, LocalDate.parse(to.toString(),format).toString());
			ResultSet rs = stmt.executeQuery();
			String defaultTime = "00:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			while (rs.next()) {
				String maPNH = rs.getString("MaPhieuNhapHang");
				String date = rs.getString("ThoiGianTao");
				LocalDateTime thoiGianTao = LocalDateTime.parse(date, formatter);
//				LocalDate thoiGianTao = LocalDate.parse(rs.getString("ThoiGianTao"));
				
				String ghiChu = rs.getString("GhiChu");
		
				double tienGiam = rs.getDouble("TongGiamGia");
				double tongTienHang = rs.getDouble("TongTienHang");
	
				
				PhieuNhapHang pnh = new PhieuNhapHang(maPNH, thoiGianTao, ghiChu, tienGiam, tongTienHang);
				dsPNH.add(pnh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPNH;
	}
	public List<PhieuNhapHang> getAllDataPNH() {
		List<PhieuNhapHang> dsPNH = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from PhieuNhapHang";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			String defaultTime = "00:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			while (rs.next()) {
				String maPNH = rs.getString("MaPhieuNhapHang");
				String date = rs.getString("ThoiGianTao");
				LocalDateTime thoiGianTao = LocalDateTime.parse(date, formatter);
//				LocalDate thoiGianTao = LocalDate.parse(rs.getString("ThoiGianTao"));
				
				String ghiChu = rs.getString("GhiChu");
				double tienGiam = rs.getDouble("TongGiamGia");
				double tongTienHang = rs.getDouble("TongTienHang");
	
				
				PhieuNhapHang pnh = new PhieuNhapHang(maPNH, thoiGianTao, ghiChu, tienGiam, tongTienHang);
				dsPNH.add(pnh);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsPNH;
	}
	
	public boolean createPNH(PhieuNhapHang pnh) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null;
		String sql = "insert into PhieuNhapHang (MaPhieuNhapHang, ThoiGianTao, GhiChu, TongGiamGia, TongTienHang, ThanhTien) "
				+ "values (?,?,?,?,?,?)";
		
		try {
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, pnh.getMaPhieu());
			stmt.setString(2, pnh.getThoiGianTao().toString());
			stmt.setString(3, pnh.getGhiChu());
			stmt.setDouble(4, pnh.getTongGiamGia());
			stmt.setDouble(5, pnh.getTongTienHang());
			stmt.setDouble(6, pnh.tinhThanhTien());
			
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	public PhieuNhapHang getPNHTheoMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from PhieuNhapHang where MaPhieuNhapHang = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			while (rs.next()) {
				String maPNH = rs.getString("MaPhieuNhapHang");
				String date = rs.getString("ThoiGianTao");
				LocalDateTime thoiGianTao = LocalDateTime.parse(date, formatter);
//				LocalDate thoiGianTao = LocalDate.parse(rs.getString("ThoiGianTao"));
			
				String ghiChu = rs.getString("GhiChu");

				double tienGiam = rs.getDouble("TongGiamGia");
				double tongTienHang = rs.getDouble("TongTienHang");
				
				PhieuNhapHang pnh = new PhieuNhapHang(maPNH, thoiGianTao, ghiChu, tienGiam, tongTienHang);
				return pnh;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
