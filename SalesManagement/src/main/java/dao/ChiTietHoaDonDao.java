package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.ConnectDB;

import entities.ChiTietHoaDon;

import entities.HangHoa;
import entities.HoaDon;

import entities.LoHang;


public class ChiTietHoaDonDao {
	public boolean addChiTietHD(ChiTietHoaDon ct) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null;
		String sql = "insert into ChiTietHoaDon (MaHoaDon, MaHangHoa, SoLuong, DonGia, ThanhTien, LoiNhuan) values(?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, ct.getHoaDon().getMaHoaDon());
			stmt.setString(2, ct.getHangHoa().getMaHangHoa());
			stmt.setInt(3, ct.getSoLuong());
			stmt.setDouble(4, ct.getDonGia());	
			stmt.setDouble(5, ct.tinhThanhTien());
			stmt.setDouble(6, ct.getLoiNhuan());
			
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	

	
	
	
	public List<ChiTietHoaDon> getDSCTHDbyMaHD(String maHD){
		List<ChiTietHoaDon> dsCTHD = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from chitiethoadon where mahoadon = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maHD);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				HangHoa hh = new HangHoaDao().timHangHoaTheoMa(rs.getString("maHangHoa"));
				int soLuong = rs.getInt("soluong");
				double donGia = rs.getDouble("dongia");
				double thanhTien = rs.getDouble("thanhtien");
				double loiNhuan = rs.getDouble("loinhuan");

				ChiTietHoaDon cthd = new ChiTietHoaDon(null, soLuong, donGia, hh,loiNhuan);
				dsCTHD.add(cthd);
			}
			return dsCTHD;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public List<ChiTietHoaDon> getCTHDFromTo(LocalDate from, LocalDate to) {
		List<ChiTietHoaDon> dsCTHD = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT sum(soluong) as soluong,mahanghoa,sum(thanhtien) as thanhtien, sum(loinhuan) as loinhuan\r\n"
					+ "FROM chitiethoadon\r\n"
					+ "LEFT JOIN hoadon ON chitiethoadon.mahoadon = hoadon.mahoadon\r\n"
					+ "WHERE DATE(hoadon.ThoiGianLapHoaDon) BETWEEN ? AND ? group by mahanghoa "
					+ "order by thanhtien desc";
			
			PreparedStatement stmt=  null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, from.toString());
			stmt.setString(2, to.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String maHD = null;
				HoaDon hd = null;
				int soLuong = rs.getInt("SoLuong");
				double donGia = rs.getDouble("thanhtien");
				String maHH = rs.getString("MaHangHoa");
				HangHoa hh = new HangHoaDao().timHangHoaTheoMa(maHH);
				double loiNhuan = rs.getDouble("LoiNhuan");
             
				ChiTietHoaDon cthd = new ChiTietHoaDon(hd, soLuong, donGia, hh,loiNhuan);
				dsCTHD.add(cthd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCTHD;
	} 
	
	
}
