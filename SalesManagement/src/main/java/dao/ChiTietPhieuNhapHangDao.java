package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.ConnectDB;
import entities.ChiTietHoaDon;
import entities.ChiTietPhieuNhapHang;
import entities.HangHoa;
import entities.HoaDon;
import entities.LoHang;
import entities.PhieuNhapHang;

public class ChiTietPhieuNhapHangDao {
	public boolean addChiTietPNH(ChiTietPhieuNhapHang ct) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null;
		String sql = "insert into ChiTietPhieuNhapHang (MaPhieuNhapHang, SoLo, MaHangHoa, SoLuong, ChietKhau, ThanhTien) "
				+ "values (?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ct.getPhieuNhapHang().getMaPhieu());
			stmt.setString(2, ct.getLoHang().getSoLo());
			stmt.setString(3, ct.getHangHoa().getMaHangHoa());
			stmt.setInt(4, ct.getSoLuong());
			stmt.setDouble(5, ct.getChietKhau());
			stmt.setDouble(6, ct.tinhThanhTien());
			
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public List<ChiTietPhieuNhapHang> getChiTietPNH(String maPhieu){
		List<ChiTietPhieuNhapHang> dsCT = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null;

		PhieuNhapHangDao pnh_dao = new PhieuNhapHangDao();
		LoHangDao lo_dao = new LoHangDao();
		String sql = "select * from ChiTietPhieuNhapHang where MaPhieuNhapHang = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maPhieu);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maPNH = rs.getString("MaPhieuNhapHang");
				PhieuNhapHang pnh = pnh_dao.getPNHTheoMa(maPNH);
				
				String soLo = rs.getString("SoLo");
				HangHoa hh = new HangHoaDao().timHangHoaTheoMa(rs.getString("maHangHoa"));
				LoHang lo = lo_dao.timLoHangTheoMa(soLo);
				int soLuong = rs.getInt("SoLuong");
				double chietKhau = rs.getDouble("ChietKhau");
				
						
				ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang(pnh, lo, hh, soLuong, chietKhau);
				dsCT.add(ct);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCT;
	}
	public List<ChiTietPhieuNhapHang> getCTPNHFromTo(LocalDate from, LocalDate to) {
		List<ChiTietPhieuNhapHang> dsCTPNH = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select mahanghoa,count(mahanghoa) as soluonglo, sum(soluong) as soluong, sum(chitietphieunhaphang.thanhtien) as thanhtien\r\n"
					+ "from chitietphieunhaphang\r\n"
					+ "LEFT JOIN phieunhaphang ON chitietphieunhaphang.maphieunhaphang = phieunhaphang.maphieunhaphang\r\n"
					+ "WHERE DATE(phieunhaphang.ThoiGianTao) BETWEEN ? AND ? \r\n"
					+ "group by mahanghoa \r\n"
					+ "order by thanhtien desc\r\n"
					+ "\r\n"
					+ "";
			
			PreparedStatement stmt=  null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, from.toString());
			stmt.setString(2, to.toString());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String maHH = rs.getString("mahanghoa");
				HangHoa hh = new HangHoaDao().timHangHoaTheoMa(maHH);
				int soLuong = rs.getInt("soluong");
				int soLuongLo = rs.getInt("soluonglo");
				LoHang lh = new LoHang(null, null, soLuongLo, null, null, 0, null);
				double thanhtien = rs.getDouble("thanhtien");
				ChiTietPhieuNhapHang ct = new ChiTietPhieuNhapHang(null, lh, hh, soLuong, thanhtien);
				dsCTPNH.add(ct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dsCTPNH;
	} 
}
