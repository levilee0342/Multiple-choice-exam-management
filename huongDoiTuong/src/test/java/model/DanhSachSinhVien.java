package model;

import java.util.ArrayList;
import java.util.List;

public class DanhSachSinhVien {
	private List<SinhVien> dsSinhVien;

    public DanhSachSinhVien() {
        dsSinhVien = new ArrayList<>();
    }

    public void themSinhVien(SinhVien sinhVien) {
        dsSinhVien.add(sinhVien);
    }

    public List<SinhVien> getDsSinhVien() {
        return dsSinhVien;
    }
}
