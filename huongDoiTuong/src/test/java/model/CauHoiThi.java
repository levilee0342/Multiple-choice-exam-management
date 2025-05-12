package model;

import javax.swing.JOptionPane;

import DAO.CauHoiThiDAO;
import DAO.MonHocDAO;

public class CauHoiThi extends MonHoc {
	private String id;
	private String noiDung;
	private String a;
	private String b;
	private String c;
	private String d;
	private String dapAn;
	private String maMH;
	private static MonHocDAO mh = new MonHocDAO(); 

    
	public CauHoiThi(String maMH, String id, String noiDung, String a, String b, String c, String d,
			String dapAn) {
		
		super(maMH, getTenMH(maMH));
		this.id = id;
		this.noiDung = noiDung;
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.dapAn = dapAn;
	}
	
	private static String getTenMH(String maMH) {
        try {
            return mh.getMonHocByMaMH(maMH).getTenMH();
        } catch (Exception e) {
            e.printStackTrace();
            return ""; 
        }
    }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public String getDapAn() {
		return dapAn;
	}
	public void setDapAn(String dapAn) {
		this.dapAn = dapAn;
	}
	
}
