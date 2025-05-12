package model;

import java.sql.Date;

public class GiaoVien extends Nguoi {
	private String maGV;
	private String hocVi;
	private String maTK;
	
	public GiaoVien() {
		super();
	}
	public GiaoVien(String ho, String ten, String cMND, Date ngaySinh, String gioiTinh, String queQuan,
			String maGV, String hocVi, String maTK) {
		super(ho, ten, cMND, ngaySinh, gioiTinh, queQuan);
		this.maGV = maGV;
		this.hocVi = hocVi;
		this.maTK = maTK;
	}
	public String getMaGV() {
		return maGV;
	}
	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}
	public String getHocVi() {
		return hocVi;
	}
	public void setHocVi(String hocVi) {
		this.hocVi = hocVi;
	}
	public String getMaTK() {
		return maTK;
	}
	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}
	
}
