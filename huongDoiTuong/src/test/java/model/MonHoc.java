package model;

public class MonHoc {
	private String maMH;
	private String tenMH;
	
	public MonHoc(String maMH) {
		super();
		this.maMH = maMH;
	}
	public MonHoc(String maMH, String tenMH) {
		super();
		this.maMH = maMH;
		this.tenMH = tenMH;
	}
	public String getMaMH() {
		return maMH;
	}
	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}
	public String getTenMH() {
		return tenMH;
	}
	public void setTenMH(String tenMH) {
		this.tenMH = tenMH;
	}
	
}
