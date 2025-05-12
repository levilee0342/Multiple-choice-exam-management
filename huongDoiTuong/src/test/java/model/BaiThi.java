package model;

public class BaiThi{
	private String maBaiThi;
	private String maSV;
	private String maMH;
	private String maDotThi;
	private int soPhutThi;
	private float diem;
	
	
	
	public BaiThi(String maBaiThi, String maSV, String maMH, String maDotThi, int soPhutThi, float diem) {
		super();
		this.maBaiThi = maBaiThi;
		this.maSV = maSV;
		this.maMH = maMH;
		this.maDotThi = maDotThi;
		this.soPhutThi = soPhutThi;
		this.diem = diem;
	}

	public String getMaBaiThi() {
		return maBaiThi;
	}

	public void setMaBaiThi(String maBaiThi) {
		this.maBaiThi = maBaiThi;
	}

	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getMaMH() {
		return maMH;
	}
	public void setMaMH(String maMH) {
		this.maMH = maMH;
	}

	public String getMaDotThi() {
		return maDotThi;
	}

	public void setMaDotThi(String maDotThi) {
		this.maDotThi = maDotThi;
	}

	public int getSoPhutThi() {
		return soPhutThi;
	}

	public void setSoPhutThi(int soPhutThi) {
		this.soPhutThi = soPhutThi;
	}

	public float getDiem() {
		return diem;
	}

	public void setDiem(float diem) {
		this.diem = diem;
	}


}
