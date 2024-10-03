package entities;

import java.time.LocalDate;

public class LoHang {
	private String soLo;
	private HangHoa hangHoa;
	private int soLuong;
	private LocalDate ngaySanXuat;
	private LocalDate hanSuDung;
	private double giaNhap;
	private LocalDate ngayNhap;
	
	
	public LoHang(String soLo, HangHoa hangHoa, int soLuong, LocalDate ngaySanXuat, LocalDate hanSuDung, double giaNhap,
			LocalDate ngayNhap) {
		super();
		this.soLo = soLo;
		this.hangHoa = hangHoa;
		this.soLuong = soLuong;
		this.ngaySanXuat = ngaySanXuat;
		this.hanSuDung = hanSuDung;
		this.giaNhap = giaNhap;
		this.ngayNhap = ngayNhap;
	}
	public String getSoLo() {
		return soLo;
	}
	public void setSoLo(String soLo) {
		this.soLo = soLo;
	}
	public HangHoa getHangHoa() {
		return hangHoa;
	}
	public void setHangHoa(HangHoa hangHoa) {
		this.hangHoa = hangHoa;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public LocalDate getNgaySanXuat() {
		return ngaySanXuat;
	}
	public void setNgaySanXuat(LocalDate ngaySanXuat) {
		this.ngaySanXuat = ngaySanXuat;
	}
	public LocalDate getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(LocalDate hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}
	
	public LocalDate getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(LocalDate ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	@Override
	public String toString() {
		return "LoHang [soLo=" + soLo + ", hangHoa=" + hangHoa + ", soLuong=" + soLuong + ", ngaySanXuat=" + ngaySanXuat
				+ ", hanSuDung=" + hanSuDung + ", giaNhap=" + giaNhap + ", ngayNhap=" + ngayNhap + "]";
	}
	
	
	
}
