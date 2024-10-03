package entities;

import java.util.List;
import java.util.Map;

public class ChiTietHoaDon {
	private HoaDon hoaDon;
	private int soLuong;
	private double donGia;
	private HangHoa hangHoa;
	private double loiNhuan;
	

	public ChiTietHoaDon() {
		// TODO Auto-generated constructor stub
	}
	

	public ChiTietHoaDon(HoaDon hoaDon, int soLuong, double donGia, HangHoa hangHoa, double loiNhuan) {
		super();
		this.hoaDon = hoaDon;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.hangHoa = hangHoa;
		this.loiNhuan = loiNhuan;
	}


	public HoaDon getHoaDon() {
		return hoaDon;
	}



	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}



	public int getSoLuong() {
		return soLuong;
	}



	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}



	public double getDonGia() {
		return donGia;
	}



	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}



	public HangHoa getHangHoa() {
		return hangHoa;
	}



	public void setHangHoa(HangHoa hangHoa) {
		this.hangHoa = hangHoa;
	}


	public double getLoiNhuan() {
		return loiNhuan;
	}


	public void setLoiNhuan(double loiNhuan) {
		this.loiNhuan = loiNhuan;
	}


	public double tinhThanhTien() {
		return soLuong*donGia;
	}

	@Override
	public String toString() {
		return "ChiTietHoaDon [hoaDon=" + hoaDon + ", soLuong=" + soLuong + ", donGia=" + donGia + ", hangHoa="
				+ hangHoa + "loiNhuan="+loiNhuan+"]";
	}

	
	
}
