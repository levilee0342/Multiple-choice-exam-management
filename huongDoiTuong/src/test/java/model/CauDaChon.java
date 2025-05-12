package model;

public class CauDaChon {
	private String maBaiThi;
	private String id;
	private String cauDaChon;
	public CauDaChon(String maBaiThi, String id, String cauDaChon) {
		super();
		this.maBaiThi = maBaiThi;
		this.id = id;
		this.cauDaChon = cauDaChon;
	}
	public String getMaSV() {
		return maBaiThi;
	}
	public void setMaSV(String maSV) {
		this.maBaiThi = maSV;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCauDaChon() {
		return cauDaChon;
	}
	public void setCauDaChon(String cauDaChon) {
		this.cauDaChon = cauDaChon;
	}
	
}
