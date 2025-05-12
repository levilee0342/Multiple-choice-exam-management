package model;

import java.sql.Date;


public class Nguoi{
    private String ho;
	private String ten;
    private String CMND;
    private Date ngaySinh;
    private String gioiTinh;
    private String queQuan;
    
	public Nguoi() {
		super();
	}
	public Nguoi(String ho, String ten, String cMND, Date ngaySinh, String gioiTinh, String queQuan) {
		super();
		this.ho = ho;
		this.ten = ten;
		CMND = cMND;
		this.ngaySinh = ngaySinh;
		this.gioiTinh = gioiTinh;
		this.queQuan = queQuan;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cMND) {
		CMND = cMND;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getQueQuan() {
		return queQuan;
	}
	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}
	
}
