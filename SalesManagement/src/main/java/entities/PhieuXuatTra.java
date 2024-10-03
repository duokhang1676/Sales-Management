package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import components.Formater;

public class PhieuXuatTra {
	private String maPhieuXuatTra;
	private LocalDateTime thoiGianTao;
	private String ghiChu;
	private double tongGiamGia;
	private double tongTienHang;
	
	public PhieuXuatTra(String maPhieuXuatTra, LocalDateTime thoiGianTao, String ghiChu,
			double tongGiamGia, double tongTienHang) {
		super();
		this.maPhieuXuatTra = maPhieuXuatTra;
		this.thoiGianTao = thoiGianTao;
		this.ghiChu = ghiChu;
		this.tongGiamGia = tongGiamGia;
		this.tongTienHang = tongTienHang;
	}

	public String getMaPhieuXuatTra() {
		return maPhieuXuatTra;
	}



	public void setMaPhieuXuatTra(String maPhieuXuatTra) {
		this.maPhieuXuatTra = maPhieuXuatTra;
	}



	public LocalDateTime getThoiGianTao() {
		return thoiGianTao;
	}



	public void setThoiGianTao(LocalDateTime thoiGianTao) {
		this.thoiGianTao = thoiGianTao;
	}



	public String getGhiChu() {
		return ghiChu;
	}



	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}




	public double getTongGiamGia() {
		return tongGiamGia;
	}



	public void setTongGiamGia(double tongGiamGia) {
		this.tongGiamGia = tongGiamGia;
	}



	public double getTongTienHang() {
		return tongTienHang;
	}



	public void setTongTienHang(double tongTienHang) {
		this.tongTienHang = tongTienHang;
	}

	

	@Override
	public String toString() {
		return "PhieuXuatTra [maPhieuXuatTra=" + maPhieuXuatTra + ", thoiGianTao=" + thoiGianTao + ", ghiChu=" + ghiChu
			+ ", tongGiamGia=" + tongGiamGia + ", tongTienHang=" + tongTienHang
				+ "]";
	}

	public double tinhThanhTien() {
		return tongTienHang-tongGiamGia;
	}

	
	
}
