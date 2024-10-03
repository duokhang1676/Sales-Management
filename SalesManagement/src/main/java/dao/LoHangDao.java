package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.ConnectDB;
import entities.HangHoa;
import entities.LoHang;

public class LoHangDao {
	public LoHangDao() {
		// TODO Auto-generated constructor stub
	}
	public List<LoHang> getDSLoTheoHSD(LocalDate hsd){
		List<LoHang> dsLoHang = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from LoHang\r\n"
					+ "where HanSuDung < ? and soluong > 0";
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hsd.toString());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String soLo = rs.getString("SoLo");
				HangHoa hangHoa = new HangHoaDao().timHangHoaTheoMa(rs.getString("MaHangHoa"));
				int soLuong = rs.getInt("SoLuong");
				LocalDate hanSuDung = LocalDate.parse(rs.getString("HanSuDung"));
				LocalDate nsx = LocalDate.parse(rs.getString("NgaySanXuat"));
				double giaNhap = rs.getDouble("GiaNhap");
				LocalDate ngayNhap = LocalDate.parse(rs.getString("NgayNhap"));
				
				LoHang loHang = new LoHang(soLo, hangHoa, soLuong, nsx, hanSuDung, giaNhap,ngayNhap);
				
				dsLoHang.add(loHang);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return dsLoHang;
	}
	public List<LoHang> getDSLoSapHetHSD(LocalDate hsd){
		List<LoHang> dsLoHang = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "SELECT * \r\n"
					+ "FROM LoHang\r\n"
					+ "WHERE HanSuDung < ? \r\n"
					+ "AND HanSuDung > DATE('now') \r\n"
					+ "AND SoLuong > 0";
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hsd.toString());
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				String soLo = rs.getString("SoLo");
				HangHoa hangHoa = new HangHoaDao().timHangHoaTheoMa(rs.getString("MaHangHoa"));
				int soLuong = rs.getInt("SoLuong");
				LocalDate hanSuDung = LocalDate.parse(rs.getString("HanSuDung"));
				LocalDate nsx = LocalDate.parse(rs.getString("NgaySanXuat"));
				double giaNhap = rs.getDouble("GiaNhap");
				LocalDate ngayNhap = LocalDate.parse(rs.getString("NgayNhap"));
				
				LoHang loHang = new LoHang(soLo, hangHoa, soLuong, nsx, hanSuDung, giaNhap,ngayNhap);
				
				dsLoHang.add(loHang);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return dsLoHang;
	}


	
	public LoHang timLoHangTheoMa(String maLo) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		
		try {
			String sql = "Select * from LoHang where SoLo = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, maLo);
	
			ResultSet rs = stmt.executeQuery();
            while(rs.next()){
            	String soLo = rs.getString("SoLo");
				HangHoa hangHoa = new HangHoaDao().timHangHoaTheoMa(rs.getString("MaHangHoa"));
				int soLuong = rs.getInt("SoLuong");
				LocalDate ngaySanXuat =LocalDate.parse(rs.getString("NgaySanXuat"));
				LocalDate hanSuDung = LocalDate.parse(rs.getString("HanSuDung"));
				double giaNhap = rs.getDouble("GiaNhap");
				
				LocalDate ngayNhap = rs.getDate("NgayNhap").toLocalDate();
				LoHang loHang = new LoHang(soLo, hangHoa, soLuong, ngaySanXuat, hanSuDung, giaNhap,ngayNhap);
				return loHang;
            }
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return null;
		
	}
	public boolean createLoHang(LoHang lo) {
		// TODO Auto-generated method stub
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "insert into LoHang values(?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			
			stmt.setString(1, lo.getSoLo());
			stmt.setString(2, lo.getHangHoa().getMaHangHoa());
			stmt.setInt(3, lo.getSoLuong());
			stmt.setString(4, Date.valueOf(lo.getNgaySanXuat()).toString());
			stmt.setString(5, Date.valueOf(lo.getHanSuDung()).toString());
			stmt.setDouble(6, lo.getGiaNhap());
			stmt.setString(7, Date.valueOf(lo.getNgayNhap()).toString());
			
			stmt.executeUpdate();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	public List<LoHang> getTop5LoTheoNgaySX(String mahh) {
		List<LoHang> dsLoHang = new ArrayList<>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "select * from LoHang where MaHangHoa = ? order by NgaySanXuat DESC";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, mahh);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String soLo = rs.getString("SoLo");
				HangHoa hangHoa = new HangHoaDao().timHangHoaTheoMa(rs.getString("MaHangHoa"));
				int soLuong = rs.getInt("SoLuong");
				LocalDate hanSuDung = LocalDate.parse(rs.getString("HanSuDung"));
				LocalDate nsx = LocalDate.parse(rs.getString("NgaySanXuat"));
				double giaNhap = rs.getDouble("GiaNhap");
				LocalDate ngayNhap = LocalDate.parse(rs.getString("NgayNhap"));
				
				LoHang loHang = new LoHang(soLo, hangHoa, soLuong, nsx, hanSuDung, giaNhap,ngayNhap);
				
				dsLoHang.add(loHang);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return dsLoHang;
	}


	
	public List<LoHang> timLoHangTheoMaHangHoa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		List<LoHang> dsLoHang = new ArrayList<>();
		try {
			String sql = "select * from LoHang where MaHangHoa = ? and SoLuong > 0  order by hansudung";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String soLo = rs.getString("SoLo");
				HangHoa hh = new HangHoaDao().timHangHoaTheoMa(rs.getString("MaHangHoa"));
				int soLuong = rs.getInt("SoLuong");
				LocalDate ngaySanXuat =LocalDate.parse(rs.getString("NgaySanXuat"));
				LocalDate hanSuDung = LocalDate.parse(rs.getString("HanSuDung"));
				double giaNhap = rs.getDouble("GiaNhap");
				LocalDate ngayNhap = LocalDate.parse(rs.getString("NgayNhap"));
				
				LoHang loHang = new LoHang(soLo, hh, soLuong, ngaySanXuat, hanSuDung, giaNhap,ngayNhap);
				dsLoHang.add(loHang);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return dsLoHang;
	}
	

	public LoHang getLoHangBySoLo(String soLo){
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "select * from LoHang\r\n"
					+ "where solo = ?";
			PreparedStatement stmt = null;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, soLo);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			HangHoa hangHoa = new HangHoaDao().timHangHoaTheoMa(rs.getString("MaHangHoa"));
			int soLuong = rs.getInt("SoLuong");
			LocalDate ngaySanXuat = rs.getDate("ngaysanxuat").toLocalDate();
			LocalDate hanSuDung = rs.getDate("HanSuDung").toLocalDate();
			double giaNhap = rs.getDouble("gianhap");
			LocalDate ngayNhap = rs.getDate("NgayNhap").toLocalDate();
				
			LoHang loHang = new LoHang(soLo, hangHoa, soLuong, ngaySanXuat, hanSuDung, giaNhap,ngayNhap);
				
			return loHang;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
	}
	
	public List<LoHang> getLoHangTheoMaHH(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		List<LoHang> dsLoHang = new ArrayList<>();
		try {
			String sql = "select * from LoHang where MaHangHoa = ? order by hansudung desc";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, ma);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String soLo = rs.getString("SoLo");
				HangHoa hh = new HangHoaDao().timHangHoaTheoMa(rs.getString("MaHangHoa"));
				int soLuong = rs.getInt("SoLuong");
				LocalDate ngaySanXuat =LocalDate.parse(rs.getString("NgaySanXuat"));
				LocalDate hanSuDung = LocalDate.parse(rs.getString("HanSuDung"));
				double giaNhap = rs.getDouble("GiaNhap");
				LocalDate ngayNhap = LocalDate.parse(rs.getString("NgayNhap"));
				
				LoHang loHang = new LoHang(soLo, hh, soLuong, ngaySanXuat, hanSuDung, giaNhap,ngayNhap);
				dsLoHang.add(loHang);
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		return dsLoHang;
	}
	public boolean capNhatSoLuongLoTheoMa( LoHang lo) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection(); 
		PreparedStatement stmt = null;			
		try {
				String sql = "update LoHang set SoLuong = ? where SoLo = ? and MaHangHoa = ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, lo.getSoLuong());
				stmt.setString(2, lo.getSoLo());
				stmt.setString(3,lo.getHangHoa().getMaHangHoa());
				stmt.executeUpdate();
				stmt.close();
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
}
