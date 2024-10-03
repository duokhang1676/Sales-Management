package entities;

public class ChiTietPhieuXuatTra {
	private PhieuXuatTra phieuXuatTraNCC;
	private LoHang loHang;
	private HangHoa hangHoa;
	private int soLuong;
	private double donGia;
	private double chietKhau;

	public ChiTietPhieuXuatTra(PhieuXuatTra phieuXuatTraNCC, LoHang loHang, HangHoa hangHoa, int soLuong, double donGia,
			double chietKhau) {
		super();
		this.phieuXuatTraNCC = phieuXuatTraNCC;
		this.loHang = loHang;
		this.hangHoa = hangHoa;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.chietKhau = chietKhau;
	}


	public PhieuXuatTra getPhieuXuatTraNCC() {
		return phieuXuatTraNCC;
	}



	public void setPhieuXuatTraNCC(PhieuXuatTra phieuXuatTraNCC) {
		this.phieuXuatTraNCC = phieuXuatTraNCC;
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



	public double getDonGia() {
		return donGia;
	}



	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}



	public double getChietKhau() {
		return chietKhau;
	}



	public void setChietKhau(double chietKhau) {
		this.chietKhau = chietKhau;
	}



	public double tinhThanhTien() {
		return soLuong * donGia - soLuong * donGia * chietKhau / 100;
	}


	@Override
	public String toString() {
		return "ChiTietPhieuXuatTra [phieuXuatTraNCC=" + phieuXuatTraNCC + ", loHang=" + loHang + ", hangHoa=" + hangHoa
				+ ", soLuong=" + soLuong + ", donGia=" + donGia + ", chietKhau=" + chietKhau + "]";
	}
}
