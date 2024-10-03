package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import db.ConnectDB;
import entities.ChiTietPhieuXuatTra;
import entities.ChiTietPhieuXuatTra;

import entities.HangHoa;
import entities.LoHang;
import entities.PhieuXuatTra;


public class ChiTietPhieuXuatTraDao {

	public boolean addChiTietPXT(ChiTietPhieuXuatTra ct) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null;
		String sql = "insert into ChiTietPhieuXuatTra (MaPhieuXuatTra, SoLo, MaHangHoa, SoLuong, DonGia,ChietKhau, ThanhTien) "
				+ "values (?,?,?,?,?,?,?)";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ct.getPhieuXuatTraNCC().getMaPhieuXuatTra());
			stmt.setString(2, ct.getLoHang().getSoLo());
			stmt.setString(3, ct.getHangHoa().getMaHangHoa());
			stmt.setInt(4, ct.getSoLuong());
			stmt.setDouble(5, ct.getDonGia());
			stmt.setDouble(6, ct.getChietKhau());
			stmt.setDouble(7, ct.tinhThanhTien());
			
			stmt.executeUpdate();
			stmt.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public List<ChiTietPhieuXuatTra> getChiTietPXT(String maPhieu){
		List<ChiTietPhieuXuatTra> dsCT = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null;
	
		PhieuXuatTraDao pxt_dao = new PhieuXuatTraDao();
		LoHangDao lo_dao = new LoHangDao();
		String sql = "select * from ChiTietPhieuXuatTra where MaPhieuXuatTra = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maPhieu);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String maPXT = rs.getString("MaPhieuXuatTra");
				PhieuXuatTra pxt = pxt_dao.getPXTTheoMa(maPXT);
				
				String soLo = rs.getString("SoLo");
				
				
				HangHoa hh = new HangHoaDao().timHangHoaTheoMa(rs.getString("maHangHoa"));
				LoHang lo = lo_dao.timLoHangTheoMa(soLo);
				int soLuong = rs.getInt("SoLuong");
				double chietKhau = rs.getDouble("ChietKhau");
				double thanhTien = rs.getDouble("ThanhTien");
						
				ChiTietPhieuXuatTra ct = new ChiTietPhieuXuatTra(pxt, lo, hh, soLuong, thanhTien, chietKhau);
				dsCT.add(ct);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsCT;
	}
}
