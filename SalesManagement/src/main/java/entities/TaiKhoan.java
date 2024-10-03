package entities;

public class TaiKhoan {
	private String tenDangNhap;
	private String matKhau;
	public TaiKhoan() {
		// TODO Auto-generated constructor stub
	}
	public TaiKhoan(String tenDangNhap, String matKhau) {
		super();
		this.tenDangNhap = tenDangNhap;
		this.matKhau = matKhau;
	}
	public String getTenDangNhap() {
		return tenDangNhap;
	}
	public void setTenDangNhap(String tenDangNhap) {
		this.tenDangNhap = tenDangNhap;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	@Override
	public String toString() {
		return "TaiKhoan [tenDangNhap=" + tenDangNhap + ", matKhau=" + matKhau + "]";
	}
	
}
