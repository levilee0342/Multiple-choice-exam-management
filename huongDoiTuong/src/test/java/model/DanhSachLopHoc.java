package model;

import java.util.ArrayList;
import java.util.List;

public class DanhSachLopHoc {
	private List<Lop> dsLopHoc;

	public DanhSachLopHoc() {
		dsLopHoc = new ArrayList<>();
	}

	public List<Lop> getDsLopHoc() {
		return dsLopHoc;
	}

	public void setDsLopHoc(List<Lop> dsLopHoc) {
		this.dsLopHoc = dsLopHoc;
	}
	
}
