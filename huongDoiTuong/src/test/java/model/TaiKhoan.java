package model;

public class TaiKhoan {
	private String maTK;
	private String password;
	private String vaiTro;
	public TaiKhoan(String maTK, String password, String vaiTro) {
		super();
		this.maTK = maTK;
		this.password = password;
		this.vaiTro = vaiTro;
	}
	public String getMaTK() {
		return maTK;
	}
	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVaiTro() {
		return vaiTro;
	}
	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}
	
	
}
