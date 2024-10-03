package entities;

public class ChiTietPhieuNhapHang {
	private PhieuNhapHang phieuNhapHang;
	private LoHang loHang;
	private HangHoa hangHoa;
	private int soLuong;
	private double chietKhau;
	
	public PhieuNhapHang getPhieuNhapHang() {
		return phieuNhapHang;
	}

	public void setPhieuNhapHang(PhieuNhapHang phieuNhapHang) {
		this.phieuNhapHang = phieuNhapHang;
	}

	public LoHang getLoHang() {
		return loHang;
	}

	public void setLoHang(LoHang loHang) {
		this.loHang = loHang;
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

	public double getChietKhau() {
		return chietKhau;
	}

	public void setChietKhau(double chietKhau) {
		this.chietKhau = chietKhau;
	}

	public ChiTietPhieuNhapHang(PhieuNhapHang phieuNhapHang, LoHang loHang, HangHoa hangHoa, int soLuong,
			double chietKhau) {
		super();
		this.phieuNhapHang = phieuNhapHang;
		this.loHang = loHang;
		this.hangHoa = hangHoa;
		this.soLuong = soLuong;
		this.chietKhau = chietKhau;
	}

	public double tinhThanhTien() {
		return soLuong*loHang.getGiaNhap()-soLuong*loHang.getGiaNhap()*chietKhau/100;
	}

	@Override
	public String toString() {
		return "ChiTietPhieuNhapHang [phieuNhapHang=" + phieuNhapHang + ", loHang=" + loHang + ", hangHoa=" + hangHoa
				+ ", soLuong=" + soLuong + ", chietKhau=" + chietKhau + "]";
	}
	
}
