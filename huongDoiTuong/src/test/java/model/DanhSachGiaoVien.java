package model;

import java.util.ArrayList;
import java.util.List;

public class DanhSachGiaoVien {
	private List<GiaoVien> dsGiaoVien;

    public DanhSachGiaoVien() {
        dsGiaoVien = new ArrayList<>();
    }

	public List<GiaoVien> getDsGiaoVien() {
		return dsGiaoVien;
	}

	public void setDsGiaoVien(List<GiaoVien> dsGiaoVien) {
		this.dsGiaoVien = dsGiaoVien;
	}
    
}
