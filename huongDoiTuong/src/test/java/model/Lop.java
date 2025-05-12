package model;

public class Lop {
	private String maLop;
	private String tenLop;
	private String nienKhoa;
	public Lop(String maLop, String tenLop, String nienKhoa) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.nienKhoa = nienKhoa;
	}
	public Lop() {
	}
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public String getTenLop() {
		return tenLop;
	}
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}
	public String getNienKhoa() {
		return nienKhoa;
	}
	public void setNienKhoa(String nienKhoa) {
		this.nienKhoa = nienKhoa;
	}
	
}
