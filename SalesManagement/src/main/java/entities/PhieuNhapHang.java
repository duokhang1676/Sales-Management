package entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import components.Formater;

public class PhieuNhapHang {
	private String maPhieu;
	private LocalDateTime thoiGianTao;
	private String ghiChu;
	private double tongGiamGia;
	private double tongTienHang;
	
	public PhieuNhapHang(String maPhieu, LocalDateTime thoiGianTao, String ghiChu,
			double tongGiamGia, double tongTienHang) {
		super();
		this.maPhieu = maPhieu;
		this.thoiGianTao = thoiGianTao;
		this.ghiChu = ghiChu;
		this.tongGiamGia = tongGiamGia;
		this.tongTienHang = tongTienHang;
	}


	public String getMaPhieu() {
		return maPhieu;
	}



	public void setMaPhieu(String maPhieu) {
		this.maPhieu = maPhieu;
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
		return "PhieuNhapHang [maPhieu=" + maPhieu + ", thoiGianTao=" + thoiGianTao + ", ghiChu=" + ghiChu
				 + ", tongGiamGia=" + tongGiamGia + ", tongTienHang=" + tongTienHang
				+ "]";
	}


	public double tinhThanhTien() {
		return tongTienHang-tongGiamGia;
	}

	
	
}
