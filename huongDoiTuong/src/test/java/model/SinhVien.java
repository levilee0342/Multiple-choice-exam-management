package model;
import java.sql.Date;

public class SinhVien extends Nguoi{
    private String maSV;
    private String maLop;
    private String maTK;
	private String SDT;
    private String email;
    private float diem;
    
    public SinhVien() {
        super("", "", "", null, "", ""); 
        this.maSV = "";
        this.maLop = "";
        this.maTK = "";
        this.SDT = "";
        this.email = "";
    }

	public SinhVien(String ho, String ten, String cMND, Date ngaySinh, String gioiTinh, String queQuan,
			String maSV, String maLop, String maTK, String SDT, String email) {
		super(ho, ten, cMND, ngaySinh, gioiTinh, queQuan);
		this.maSV = maSV;
		this.maLop = maLop;
		this.maTK = maTK;
		this.SDT = SDT;
		this.email = email;
	}
		


	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public String getMaTK() {
		return maTK;
	}
	public void setMaTK(String maTK) {
		this.maTK = maTK;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDiem(float diem) {
        this.diem = diem;
    }

    public float getDiem() {
        return diem;
    }
}
