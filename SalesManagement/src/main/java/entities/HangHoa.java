package entities;

public class HangHoa {
	private String maHangHoa;
	private String tenHangHoa;
	private String nhomHang;
	private String donViTinh;
	private String moTa;
	private String maVach;
	private double giaBan;
	private int soLuongDinhMuc;
	private int soLuongCanhBao;
	public HangHoa(String maHangHoa, String tenHangHoa, String nhomHang, String donViTinh, String moTa, String maVach,
			double giaBan, int soLuongDinhMuc, int soLuongCanhBao) {
		super();
		this.maHangHoa = maHangHoa;
		this.tenHangHoa = tenHangHoa;
		this.nhomHang = nhomHang;
		this.donViTinh = donViTinh;
		this.moTa = moTa;
		this.maVach = maVach;
		this.giaBan = giaBan;
		this.soLuongDinhMuc = soLuongDinhMuc;
		this.soLuongCanhBao = soLuongCanhBao;
	}
	public String getMaHangHoa() {
		return maHangHoa;
	}
	public void setMaHangHoa(String maHangHoa) {
		this.maHangHoa = maHangHoa;
	}
	public String getTenHangHoa() {
		return tenHangHoa;
	}
	public void setTenHangHoa(String tenHangHoa) {
		this.tenHangHoa = tenHangHoa;
	}
	public String getNhomHang() {
		return nhomHang;
	}
	public void setNhomHang(String nhomHang) {
		this.nhomHang = nhomHang;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public String getMoTa() {
		return moTa;
	}
	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}
	public String getMaVach() {
		return maVach;
	}
	public void setMaVach(String maVach) {
		this.maVach = maVach;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public int getSoLuongDinhMuc() {
		return soLuongDinhMuc;
	}
	public void setSoLuongDinhMuc(int soLuongDinhMuc) {
		this.soLuongDinhMuc = soLuongDinhMuc;
	}
	public int getSoLuongCanhBao() {
		return soLuongCanhBao;
	}
	public void setSoLuongCanhBao(int soLuongCanhBao) {
		this.soLuongCanhBao = soLuongCanhBao;
	}
	@Override
	public String toString() {
		return "HangHoa [maHangHoa=" + maHangHoa + ", tenHangHoa=" + tenHangHoa + ", nhomHang=" + nhomHang
				+ ", donViTinh=" + donViTinh + ", moTa=" + moTa + ", maVach=" + maVach + ", giaBan=" + giaBan
				+ ", soLuongDinhMuc=" + soLuongDinhMuc + ", soLuongCanhBao=" + soLuongCanhBao + "]";
	}
	
	
	
}
