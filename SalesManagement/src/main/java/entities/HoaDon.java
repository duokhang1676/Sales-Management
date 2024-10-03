package entities;

import java.time.LocalDateTime;

import components.Formater;

public class HoaDon {
	private String maHoaDon;
	private LocalDateTime thoiGianLapHoaDon;
	private double tienKhachTra;
	private String ghiChu;
	private double tongTien;
	
	public HoaDon(String maHoaDon, LocalDateTime thoiGianLapHoaDon, double tienKhachTra, String ghiChu,
			double tongTien) {
		super();
		this.maHoaDon = maHoaDon;
		this.thoiGianLapHoaDon = thoiGianLapHoaDon;
		this.tienKhachTra = tienKhachTra;
		this.ghiChu = ghiChu;
		this.tongTien = tongTien;
	}
	
	
	public String getMaHoaDon() {
		return maHoaDon;
	}



	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}



	public LocalDateTime getThoiGianLapHoaDon() {
		return thoiGianLapHoaDon;
	}



	public void setThoiGianLapHoaDon(LocalDateTime thoiGianLapHoaDon) {
		this.thoiGianLapHoaDon = thoiGianLapHoaDon;
	}



	public double getTienKhachTra() {
		return tienKhachTra;
	}



	public void setTienKhachTra(double tienKhachTra) {
		this.tienKhachTra = tienKhachTra;
	}



	public String getGhiChu() {
		return ghiChu;
	}



	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", thoiGianLapHoaDon=" + thoiGianLapHoaDon + ", tienKhachTra="
				+ tienKhachTra + ", ghiChu=" + ghiChu + ", tongTien=" + tongTien + "]";
	}


	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public double tinhTienThua() {
		return Formater.roundToNearest500(tienKhachTra-tongTien);
	}
	
}
